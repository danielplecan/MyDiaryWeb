package mydiaryweb.module.mdqa.question_answering.handler;

import mydiaryweb.module.mdqa.question_answering.Question;
import mydiaryweb.module.mdqa.question_answering.QuestionType;
import mydiaryweb.module.mdqa.model.Action;
import mydiaryweb.module.mdqa.model.ActionCollection;
import mydiaryweb.module.mdqa.verbalize.Verbalize;

import java.util.List;

public abstract class AbstractHandler {

    ActionCollection actionCollection = new ActionCollection();

    QuestionType qType;

    Verbalize verbalize = new Verbalize();

    public ActionCollection getActionCollection() {
        return actionCollection;
    }

    public void setActionCollection(ActionCollection actions) {
        this.actionCollection = actions;
    }

    public List<Action> getDayActions() {
        
        return getActionCollection().getActions();
    }

    public void setDayActions(List<Action> dayActions) {
        this.actionCollection.setActions(dayActions);
    }

    public QuestionType getQuestionType() {
        return qType;
    }

    public void setQuestionType(QuestionType qType) {
        this.qType = qType;
    }

    public abstract String answerQuestion(Question quest);
}
