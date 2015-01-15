package mydiaryweb.module.mdqa.answer;

import mydiaryweb.module.mdqa.nlp.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import edu.stanford.nlp.time.SUTime;

public class Answer {

    private String answer;
    private StanfordNLP nlp;
    private HashMap<String, SUTime.Temporal> dateMap;

    public Answer() {
        init();
    }

    private void init() {
        answer = new String();
        nlp = new StanfordNLP();
        dateMap = new HashMap<String, SUTime.Temporal>();
    }

    public void setAnswer(String Answer) {
        this.answer = Answer;
    }

    private String[] splitAnswer() {
        return answer.split(" ");
    }

    public List<String> getWords() {
        List<String> retVal = new ArrayList<String>();
        List<String> posTags = nlp.getPosTags(this.answer);

        for (int index = 0; index < splitAnswer().length; index++) {
            String str = posTags.get(index) + "|" + splitAnswer()[index];
            retVal.add(str);
        }

        return retVal;

    }

    private List<String> retValHashMap(HashMap<String, SUTime.Temporal> map) {
        List<String> retVal = new ArrayList<String>();
        Iterator<String> keySetIterator = map.keySet().iterator();

        while (keySetIterator.hasNext()) {
            String key = keySetIterator.next();
            retVal.add(key);
        }

        return retVal;
    }

    public List<String> extractTemporalData() {
        DateTimeNLP dt = new DateTimeNLP();
        HashMap<String, SUTime.Temporal> map = dt.extractTemporalDataWithTodayReference(this.answer);
        return retValHashMap(map);

    }

    public List<String> getWordsByPosType(String type) {
        List<String> retVal = new ArrayList<String>();

        for (int i = 0; i < getWords().size(); i++) {
            if (getWords().get(i).contains(type)) {
                retVal.add(getWords().get(i));
            }
        }

        return retVal;

    }

    public void print() {
        for (int index = 0; index < getWords().size(); index++)
            System.out.println(getWords().get(index));

        extractTemporalData();
        List<String> tst = getWordsByPosType("NN");

        System.out.println("----------------");
        for (int index = 0; index < tst.size(); index++)
            System.out.println(tst.get(index));
        System.out.println("----------------");
    }

}
