package mydiaryweb.module.mdqa.question_answering.handler;

import edu.stanford.nlp.time.SUTime;
import mydiaryweb.module.mdqa.question_answering.Question;
import mydiaryweb.module.mdqa.question_answering.QuestionType;
import mydiaryweb.module.mdqa.question_answering.score.ActionScore;
import mydiaryweb.module.mdqa.question_answering.score.ScoreCounter;
import mydiaryweb.module.mdqa.model.Action;
import mydiaryweb.module.mdqa.nlp.DateTimeNLP;
import mydiaryweb.module.mdqa.nlp.FactoryNLP;
import mydiaryweb.module.mdqa.nlp.StanfordNLP;
import mydiaryweb.module.mdqa.nlp.StopWords;
import mydiaryweb.module.mdqa.provider.criteria.*;
import mydiaryweb.module.mdqa.util.StringUtil;
import org.joda.time.DateTime;

import java.util.*;

public class Location extends AbstractHandler {

    QuestionType qType = QuestionType.LOC;

    Question currentQuest;

    ScoreCounter scoreCounter;

    public ScoreCounter getScoreCounter() {
        return scoreCounter;
    }

    public void setScoreCounter(ScoreCounter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    public Question getCurrentQuest() {
        return currentQuest;
    }

    public void setCurrentQuest(Question currentQuest) {
        this.currentQuest = currentQuest;
    }

    /**
     * Multiple approaches to answer a location question
     * 1. Find time info filter actions with that time info & create a score based on NER Location targeting
     * 2. Identify a person using NER then filter based on person criteria using NER
     *
     * @param quest
     * @return
     */
    @Override
    public String answerQuestion(Question quest) {

        setCurrentQuest(quest);

        //Score instance
        ScoreCounter scoreCounter = new ScoreCounter();
        setScoreCounter(scoreCounter);

        String answer = "";

        List<Action> answerActionList = new ArrayList<Action>();

        //answerBasedOnTimeData
        answerActionList = answerBasedOnTempData();
        if (answerActionList.size() > 0) {
            return getAnswerFromActionResults(answerActionList);
        }

        //answerBasedOnPerson
        answerActionList = answerBasedOnPerson();
        return getAnswerFromActionResults(answerActionList);
    }

    protected List<Action> answerBasedOnTempData() {
        Set<Action> resultActions = new HashSet<Action>();

        List<Action> criteriaActions = new ArrayList<Action>();

        DateTimeNLP dtNLP = FactoryNLP.inst().getDateTimeNLP();
        //Where type question
        //step1 extract time info using datetime nlp
        //step2 get actions using time info
        //step3 use ner/another criteria to extract locations
        DateTime referenceDay = getTimeAnalysisReferenceDay();
        //System.out.println(referenceDay);

        //TRY temp data approach
        HashMap<String, SUTime.Temporal> tempData;
        tempData = dtNLP.extractTemporalData(currentQuest.getQuestion(), referenceDay);

        //we have temporal data
        if (tempData.isEmpty()) {
            //at this point is empty so it does the trick
            return criteriaActions;
        }

        //loop every entry
        for (Map.Entry<String, SUTime.Temporal> tempInfo : tempData.entrySet()) {
            SUTime.Temporal suTempData = tempInfo.getValue();
            //check if we have an range
            if (isDateRange(suTempData)) {
                criteriaActions = answerBasedOnRange(suTempData);
            } else {
                criteriaActions = answerBasedOnTime(suTempData);
            }

            resultActions.addAll(criteriaActions);
        }

        List<Action> resultList = new ArrayList<Action>(resultActions);
        return resultList;
    }


    protected List<Action> answerBasedOnPerson() {

        Set<Action> resultActions = new HashSet<Action>();

        //get all words that are ProperNouns by using pos
        List<String> words = currentQuest.getWordsByPosType("NNP");
        words.addAll(currentQuest.getWordsByNerType("NNPS"));

        //get all words that are Person by using NER
        words.addAll(currentQuest.getWordsByNerType("PERSON"));
        for (String properNWord : words) {
            PersonCriteria p = new PersonCriteria(properNWord);
            List<Action> personActions = p.meetCriteria(actionCollection.getActions());

            //Remove action with no location set or empty
            personActions = filterNoLocation(personActions);

            //increment score for all remaining
            scoreCounter.incAll(personActions);

            //score results using word count & nlp ops match
            scoreResultsByNLP(personActions);

            resultActions.addAll(personActions);
        }

        List<Action> resultList = new ArrayList<Action>(resultActions);
        return resultList;

    }

    protected boolean isDateRange(SUTime.Temporal tempInfo) {
        int durationInSeconds = tempInfo.getRange().getDuration().getJodaTimeDuration().toStandardSeconds().getSeconds();
        //we consider it to be a range if duration is more than 4 minutes
        if (durationInSeconds > 60 * 4) {
            return true;
        }

        return false;
    }


    protected List<Action> answerBasedOnRange(SUTime.Temporal tempInfo) {

        DateTime referenceDay = getTimeAnalysisReferenceDay();

        List<Action> ctActions = new ArrayList<Action>();
        if (!isDateRange(tempInfo)) {
            return ctActions;
        }

        DateTime dateStart = tempInfo.getRange().beginTime().getJodaTimePartial().toDateTime(referenceDay);
        DateTime dateEnd =   tempInfo.getRange().endTime().getJodaTimePartial().toDateTime(referenceDay);

        /*LogUtil.d(dateStart);
        LogUtil.d(dateEnd);*/

        DateCriteria dtGreater = new DateCriteria(dateStart);
        DateCriteria dtLower = new DateCriteria(dateEnd);

        dtGreater.greater();
        dtLower.lower();

        ctActions = new AndCriteria(dtGreater, dtLower).meetCriteria(actionCollection.getActions());

        /*LogUtil.d(ctActions.size());*/
        timeBasedAnalysis(ctActions);

        return ctActions;

    }

    protected List<Action> answerBasedOnTime(SUTime.Temporal tempInfo) {

        DateTime dateCt = tempInfo.getTime().getJodaTimeInstant().toDateTime();
//        System.out.println(dateCt);
        DateCriteria dtCriteria = new DateCriteria(dateCt);

        //for now set for equal
        dtCriteria.eq();

        //filter based on date criteria
        List<Action> ctActions = dtCriteria.meetCriteria(actionCollection.getActions());

        timeBasedAnalysis(ctActions);

        return (ctActions);

    }

    protected void timeBasedAnalysis(List<Action> ctActions) {
        //if we a set of actions we may further reduce by analyzing question
        //analysis based on eg verb get synonyms and compare with the verbs in the action set movement etc

        //Remove action with no location set or empty
        ctActions = filterNoLocation(ctActions);

        //increment score for all remaining
        scoreCounter.incAll(ctActions);

        //score results using location ner
        scoreLocationByNer(ctActions);

        //score results using word count & nlp ops match
        scoreResultsByNLP(ctActions);

    }

    protected String getAnswerFromActionResults(List<Action> resultActions) {

        //scoreCounter.print();

        if (resultActions.size() == 0) {
            String res = "Cannot determine an answer!";
            return res;
        }

        if (!scoreCounter.scoreIsNotEmpty()) {
            //no answer was scored
            //what to do s
            //return first location
            return extractAnswerFromAction(resultActions.get(0));
        }

        //sort answers based on sort counter
        List<Action> sortedResults = getSortedActionResults(resultActions);
        return extractAnswerFromAction(sortedResults.get(0));
    }

    protected String extractAnswerFromAction(Action answerAction) {
        return verbalize.verbalizeAction(answerAction, currentQuest);
        //return answerAction.getLocation().stringify();
    }

    protected void printFirstResultLocation(List<Action> actions) {
        for (Action action : actions) {
            //Now we can do the following either check location is available or do some NER analysis
            if (action.getLocation() != null) {
                System.out.println(action.getLocation());
            }
        }
    }


    protected void scoreLocationByNer(List<Action> actions) {
        for (Action action : actions) {
            String location = action.getLocation().stringify();
            List<String> tags = getNERTags(location);
            for (String nerTag : tags) {
                if (nerTag.equals("LOCATION")) {
                    //inc score for this action
                    scoreCounter.inc(action);
                }
            }
        }
    }

    protected void scoreResultsByNLP(List<Action> actions) {
        StanfordNLP nlp = FactoryNLP.inst().getStanfordNLP();
        StopWords stopWords = FactoryNLP.inst().getStopWords();

        //Get question focus words
        List<String> focusWords = currentQuest.getFocusWords();

        //apply stemming
        List<String> stemmedQuestionWords = nlp.getStemmedWords(StringUtil.listToString(focusWords));

        for (Action action : actions) {
            String data = action.stringify();

            //Remove stop wordsW
            data = stopWords.removeStopWords(data);

            //apply stemming on action data also
            data = StringUtil.listToString(nlp.getStemmedWords(data));

            //check each matched words and inc score
            //TODO apply similarity etc & more advanced algos
            for (String qWord : stemmedQuestionWords) {
                if (data.contains(qWord)) {
                    //inc score for this action
                    scoreCounter.inc(action);
                }
            }
        }
    }

    protected List<Action> filterNoLocation(List<Action> actions) {
        List<Action> newActions = new ArrayList<Action>();
        for (Action action : actions) {
            if (action.getLocation() != null) {
                newActions.add(action);
            }
        }
        return newActions;
    }

    protected List<String> getNERTags(String data) {
        return FactoryNLP.inst().getStanfordNLP().getNERTags(data);
    }


    protected List<Action> getSortedActionResults(List<Action> resultActions) {
        if (!scoreCounter.scoreIsNotEmpty()) {
            return null;
        }

        //sort scorelist
        scoreCounter.sort();

        List<ActionScore> scoreList = scoreCounter.getScoreList();
        List<Action> sorted = new ArrayList<Action>();
        UIDCriteria uidCriteria;

        for (ActionScore score : scoreList) {
            uidCriteria = new UIDCriteria(score.getActionId());
            sorted.addAll(uidCriteria.meetCriteria(resultActions));
        }

        return sorted;

    }

    protected DateTime getTimeAnalysisReferenceDay(){
        DateTime referenceDay = getActionCollection().getDateForCollection();
        if (referenceDay == null){

            //MUST BE UTC forced on app bootup
            referenceDay = new DateTime();
        }

        return referenceDay;
    }
}
