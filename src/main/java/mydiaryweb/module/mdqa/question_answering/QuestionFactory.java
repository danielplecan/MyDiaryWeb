package mydiaryweb.module.mdqa.question_answering;

import mydiaryweb.module.mdqa.nlp.FactoryNLP;
import mydiaryweb.module.mdqa.nlp.StanfordNLP;

public class QuestionFactory {
    private static QuestionFactory instance = null;
    protected QuestionFactory() {
    }
    public static QuestionFactory inst() {
        if(instance == null) {
            instance = new QuestionFactory();
        }
        return instance;
    }

    public Question fromText(String question){
        Question q = new Question(question);
        StanfordNLP nlp = FactoryNLP.inst().getStanfordNLP();
        q.setQuestWords(nlp.getSentWords(question));
        q.setQuestNER(nlp.getNERTags(question));
        q.setQuestPos(nlp.getPosTags(question));
        return q;
    }
}
