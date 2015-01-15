package mydiaryweb.module.mdqa;

import mydiaryweb.module.mdqa.answer.AnswerChecking;
import mydiaryweb.module.mdqa.model.*;
import mydiaryweb.module.mdqa.question_answering.Question;
import mydiaryweb.module.mdqa.question_answering.QuestionFactory;
import mydiaryweb.module.mdqa.question_answering.classifier.Classifier;
import mydiaryweb.module.mdqa.question_answering.handler.AbstractHandler;
import mydiaryweb.module.mdqa.question_answering.handler.HandlerFactory;
import mydiaryweb.module.mdqa.question_asking.QuestionAsking;
import mydiaryweb.module.mdqa.question_asking.QuestionObject;
import mydiaryweb.module.mdqa.question_asking.AnswerProcessing;
import mydiaryweb.entity.inferences.Main;

import java.util.*;

public class Start {
    QuestionAsking questionAsking;
    AnswerProcessing answerProcessing;
    List<Action> actions = new ArrayList<Action>();

    public Start() {
        questionAsking = new QuestionAsking();
        answerProcessing = new AnswerProcessing();
        // bot mode set on PANDORABOTS
        answerProcessing.setBotMode("P");
    }

    public String processResponse(String userInput) {
        if (userInput.charAt(userInput.length() - 1) == '?') {
            // question
            return this.answer(userInput);
        } else {
            QuestionObject qObj = questionAsking.askQuestion();

            if (checkAnswer(userInput) == null) {
                // correct question
                return "I'm sorry, but I you did not do this";
            } else {
                // ask question
                return answerProcessing.processAnswer(userInput, qObj);
            }
        }
    }

    public void setData(List<Main> data) {
        List<Action> actions = new ArrayList<>();
        for (Main main : data) {
            Action action = new Action();

            // date
            ActionDate actionDate = new ActionDate(main.getDate());
            action.setActionDate(actionDate);

            // location
            Location location = new Location();
            location.setLocation(main.getAction().getLocation().getDetails());
            location.setProximity(main.getAction().getLocation().getProximity());
            location.setAddress(main.getAction().getLocation().getAddress());
            location.setVehicleName(main.getAction().getLocation().getVehicleName());
            action.setLocation(location);

            // sounds
            Sound sound = new Sound();
            sound.setSound(main.getAction().getSound().getSoundName());
            action.setSound(sound);

            // movement
            Movement movement = new Movement();
            movement.setMovement(main.getAction().getMovement().getMovementType());
            action.setMovement(movement);

            // faces
            Face face = new Face();
            face.setName(main.getAction().getFace().getFaceName());
            actions.add(action);
        }
        this.actions = actions;
    }

    public Action checkAnswer(String answer) {
        AnswerChecking ac = new AnswerChecking();
        return ac.CheckAnswer(answer, getDayActions());
    }

    public String answer(String question) {
        Question quest = QuestionFactory.inst().fromText(question);
        List<Action> dayActions = getDayActions();


        Classifier classifier = new Classifier();
        classifier.setQuestionType(quest);

        AbstractHandler l = HandlerFactory.inst().getHandlerForQuestionType(
                quest.getQuestionType(), dayActions);
        return l.answerQuestion(quest);
    }

    public List<Action> getDayActions() {
        return this.actions;
    }
}
