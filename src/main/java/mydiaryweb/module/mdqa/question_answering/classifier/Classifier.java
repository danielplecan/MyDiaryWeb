package mydiaryweb.module.mdqa.question_answering.classifier;

import mydiaryweb.module.mdqa.question_answering.Question;
import mydiaryweb.module.mdqa.question_answering.QuestionType;

public class Classifier implements QuestionClassifier {

    @Override
    public void setQuestionType(Question q) {

        String s = q.firstWord();
        if (s.equals("When")) {
            q.setQuestionType(QuestionType.TIME);
        } else if (s.equals("Where")) {
            q.setQuestionType(QuestionType.LOC);
        } else if (s.equals("What")) {
            q.setQuestionType(QuestionType.ENTY);
        } else if (s.equals("Who")) {
            q.setQuestionType(QuestionType.HUM);
        } else if (s.equals("How")) {
            if (q.secondWord().equals("many") || q.secondWord().equals("much")) {
                q.setQuestionType(QuestionType.NUM);
            } else {
                q.setQuestionType(QuestionType.DESC);
            }
        }
    }
}
