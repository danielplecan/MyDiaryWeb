package mydiaryweb.module.mdqa.question_answering.handler;

import mydiaryweb.module.mdqa.question_answering.Question;
import mydiaryweb.module.mdqa.question_answering.QuestionType;
import mydiaryweb.module.mdqa.model.Action;
import mydiaryweb.module.mdqa.provider.criteria.LocationCriteria;
import mydiaryweb.module.mdqa.provider.criteria.OrCriteria;
import mydiaryweb.module.mdqa.provider.criteria.PersonCriteria;

import java.util.ArrayList;
import java.util.List;


public class Time extends AbstractHandler {

    QuestionType qType = QuestionType.LOC;

    Question currentQuest;

    public void setCurrentQuest(Question currentQuest) {
        this.currentQuest = currentQuest;
    }

    @Override
    public String answerQuestion(Question question) {
        setCurrentQuest(question);

        Action match;
        // 1. Extract a location // When I went to Faculty ?
        List<Action> dayActions = getDayActions();
        List<String> entities;
        entities = this.getEntities(question);
        match = this.getMatchingActions(entities, dayActions);
        if (match != null) {
            return extractAnswerFromAction(match);
        }

        entities = this.getNames(question);
        match = this.getMatchingActions(entities, dayActions);
        if (match != null) {
            return extractAnswerFromAction(match);
        }

        entities = this.getNouns(question);
        match = this.getMatchingActions(entities, dayActions);
        if (match != null) {
            return extractAnswerFromAction(match);
        }
        return extractAnswerFromAction(null);
    }

    protected Action getMatchingActions(List<String> entities, List<Action> dayActions) {
        Action match = null;
        int maxScore = 0;
        for (Action dayAction : dayActions) {
            int score = 0;
            for (String entity : entities) {
                List<Action> act = new ArrayList<Action>();
                act.add(dayAction);

                LocationCriteria l = new LocationCriteria(entity);
                PersonCriteria p = new PersonCriteria(entity);

                List<Action> partialMatches = new OrCriteria(l, p).meetCriteria(act);
                if (!partialMatches.isEmpty()) {
                    score += 1;
                    break;
                }
            }
            if (score > maxScore) {
                maxScore = score;
                match = dayAction;
            }
        }
        return match;
    }

    protected String extractAnswerFromAction(Action answerAction) {
        if (answerAction == null) {
            return "Cannot determine an answer!";
        }
        return verbalize.verbalizeAction(answerAction, currentQuest);
    }

    protected List<String> getEntities(Question question) {
        List<String> a = question.getWordsByNerType("PERSON");
        a.addAll(question.getWordsByNerType("LOCATION"));
        return a;
    }

    protected List<String> getNames(Question question) {
        List<String> a = question.getWordsByPosType("NNP");
        a.addAll(question.getWordsByNerType("NNPS"));
        return a;
    }

    protected List<String> getNouns(Question question) {
        List<String> a = question.getWordsByPosType("NN");
        a.addAll(question.getWordsByNerType("NNS"));
        return a;
    }

}
