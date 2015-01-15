package mydiaryweb.module.mdqa.question_answering.handler;

import mydiaryweb.module.mdqa.question_answering.QuestionType;
import mydiaryweb.module.mdqa.model.Action;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class HandlerFactory {
    //question type enum map
    Map<QuestionType, AbstractHandler> handlerTypeMap;

    private static HandlerFactory instance = null;
    protected HandlerFactory() {
        handlerTypeMap = new EnumMap<QuestionType, AbstractHandler>(QuestionType.class);
    }

    public static HandlerFactory inst() {
        if(instance == null) {
            instance = new HandlerFactory();
        }
        return instance;
    }

    public AbstractHandler getHandlerForQuestionType(QuestionType qType){
        if (!handlerTypeMap.containsKey(qType)){
            ensureHandler(qType);
        }

        return handlerTypeMap.get(qType);
    }

    public AbstractHandler getHandlerForQuestionType(QuestionType qType, List<Action> dayActions){
        AbstractHandler h = getHandlerForQuestionType(qType);
        h.setDayActions(dayActions);
        return h;
    }

    protected void ensureHandler(QuestionType qType){
        switch (qType){
        	case HUM:
        		handlerTypeMap.put(qType, new Human());
        		break;
            case LOC:
                handlerTypeMap.put(qType, new Location());
                break;
            case TIME:
                handlerTypeMap.put(qType, new Time());
                break;
           
        }
    }
}
