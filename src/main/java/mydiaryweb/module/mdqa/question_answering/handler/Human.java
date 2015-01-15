package mydiaryweb.module.mdqa.question_answering.handler;

import java.util.ArrayList;
import java.util.List;

import mydiaryweb.module.mdqa.model.Action;
import mydiaryweb.module.mdqa.model.ActionDate;
import mydiaryweb.module.mdqa.provider.criteria.FaceCriteria;
import mydiaryweb.module.mdqa.provider.criteria.LocationCriteria;
import mydiaryweb.module.mdqa.question_answering.Question;
import mydiaryweb.module.mdqa.question_answering.QuestionType;

public class Human extends AbstractHandler {

    QuestionType qType = QuestionType.HUM;

    Question question;

    public QuestionType getqType() {
        return qType;
    }

    public Question getQuestion() {
        return question;
    }

    public void setCurrentQuest(Question question) {
        this.question = question;
    }

    @Override
    public String answerQuestion(Question question) {

        String answer = "Couldn't indentify face.";
        this.setCurrentQuest(question);

        Action match;
        List<Action> dayActions = getDayActions();
        List<String> entities;

        //take only actions that have face not null
        FaceCriteria fc = new FaceCriteria();
        List<Action> matches = fc.meetCriteria(dayActions);

        if (matches.isEmpty()) {
            answer = "We didn't indentify anyone during the day.";
        } else {
            //find answer after time
            entities = this.getTime();
            if (entities.size() != 0) {
                match = this.matchAnswerByTime(entities, matches);
                if (match != null) {
                    return this.extractAnswerFromAction(match);
                }
            }
            //find answer after location
            entities = this.getLocations();
            match = this.matchAnswerByLocation(entities, matches);
            if (match != null) {
                return this.extractAnswerFromAction(match);
            }

            entities = this.getNames();
            match = this.matchAnswerByLocation(entities, matches);
            if (match != null) {
                return this.extractAnswerFromAction(match);
            }

            entities = this.getNouns();
            match = this.matchAnswerByLocation(entities, matches);
            if (match != null) {
                return this.extractAnswerFromAction(match);
            }
        }

        return answer;
    }

    public Action matchAnswerByTime(List<String> entities, List<Action> matches) {
        Action result = null;
        for (Action act : matches) {
            String max = "01:00", min = "23:59";
            ActionDate ad = act.getActionDate();
            String data = ad.toString();
            String[] parts = data.split(" ");

            //there is just one time reference in question
            if (entities.size() == 1) {
                if (this.contains("before")) {
                    if (entities.get(0).compareTo(parts[1]) > 0) {
                        if (max.compareTo(entities.get(0)) < 0) {
                            max = entities.get(0);
                            result = act;
                        }
                    }

                } else if (this.contains("after")) {
                    if (entities.get(0).compareTo(parts[1]) < 0) {
                        if (min.compareTo(entities.get(0)) > 0) {
                            min = entities.get(0);
                            result = act;
                        }
                    } else {
                        if (entities.get(0).equals(parts[1]))
                            result = act;
                    }
                }
            } else
                //there are two time references in the question
                if (entities.size() == 2) {
                    if (entities.get(0).compareTo(parts[1]) <= 0 && entities.get(1).compareTo(parts[1]) >= 0) {
                        if (min.compareTo(entities.get(0)) > 0) {
                            min = parts[1];
                            result = act;
                        }
                    }
                }
        }
        return result;
    }

    public boolean contains(String w) {
        List<String> words = question.getQuestWords();
        for (String word : words) {
            if (word.equals(w))
                return true;
        }
        return false;
    }

    public Action matchAnswerByLocation(List<String> entities, List<Action> matches) {

        Action match = null;
        int maxScore = 0;
        for (Action action : matches) {
            int score = 0;
            for (String entity : entities) {

                List<Action> act = new ArrayList<Action>();
                act.add(action);

                LocationCriteria lc = new LocationCriteria(entity);

                List<Action> partialMatches = lc.meetCriteria(act);
                if (!partialMatches.isEmpty()) {
                    score++;
                    break;
                }
            }
            if (score > maxScore) {
                maxScore = score;
                match = action;
            }
        }
        return match;
    }

    public String extractAnswerFromAction(Action answerAction) {
        if (answerAction == null) {
            return "Cannot determine an answer!";
        }
        return verbalize.verbalizeAction(answerAction, question);
    }

    public List<String> getLocations() {

        List<String> locations = question.getWordsByNerType("LOCATION");
        return locations;
    }

    public List<String> getNames() {

        List<String> names = question.getWordsByNerType("PERSON");
        return names;
    }

    public List<String> getNouns() {

        List<String> nouns = question.getWordsByPosType("NN");
        nouns.addAll(question.getWordsByNerType("NNS"));
        return nouns;
    }

    public List<String> getTime() {

        List<String> time = question.getWordsByNerType("TIME");
        return time;
    }
}
