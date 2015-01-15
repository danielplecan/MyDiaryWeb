package mydiaryweb.module.mdqa.question_answering;

import java.util.ArrayList;
import java.util.List;

public class Question {

    public String question;

    public List<String> getQuestWords() {
        return questWords;
    }

    public void setQuestWords(List<String> questWords) {
        this.questWords = questWords;
    }

    public List<String> questWords = new ArrayList<String>();
    public List<String> questPos = new ArrayList<String>();
    public List<String> questNER = new ArrayList<String>();

    public Question(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    //should this be question type instance class
    public QuestionType questionType;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getQuestPos() {
        return questPos;
    }

    public void setQuestPos(List<String> questPos) {
        this.questPos = questPos;
    }

    public List<String> getQuestNER() {
        return questNER;
    }

    public void setQuestNER(List<String> questNER) {
        this.questNER = questNER;
    }

    public List<String> getWordsByPosType(String type) {
        List<String> filtered = new ArrayList<String>();

        for (int i = 0; i < questPos.size(); i++) {
            if (questPos.get(i).contains(type)) {
                filtered.add(questWords.get(i));
            }
        }

        return filtered;

    }

    public List<String> getWordsByPosType(List<String> posTags) {
        List<String> posTagsList  = new ArrayList<String>();
        for (String posTag : posTags){
            posTagsList.addAll(getWordsByPosType(posTag));
        }
        return posTagsList;
    }

    public List<String> getFocusWords() {
        List<String> posTagsList  = new ArrayList<String>();
        posTagsList.add("NN");
        posTagsList.add("VB");
        posTagsList.add("JJ");
        posTagsList.add("NNS");
        posTagsList.add("NNP");

        return getWordsByPosType(posTagsList);
    }


    public List<String> getWordsByNerType(String type) {
        List<String> filtered = new ArrayList<String>();
        for (int i = 0; i < questNER.size(); i++) {
            if (questNER.get(i).contains(type)) {
                filtered.add(questWords.get(i));
            }
        }

        return filtered;

    }

    public String firstWord() {
        return questWords.get(0);
    }


    public String secondWord() {
        return questWords.get(1);
    }
}
