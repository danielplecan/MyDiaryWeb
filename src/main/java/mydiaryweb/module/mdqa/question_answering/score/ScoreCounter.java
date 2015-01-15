package mydiaryweb.module.mdqa.question_answering.score;

import mydiaryweb.module.mdqa.model.Action;

import java.util.*;

public class ScoreCounter {

    List<ActionScore> scoreList;
    Map<Integer, Integer> indiceMap;

    public ScoreCounter() {
        this.scoreList = new ArrayList<ActionScore>();
        this.indiceMap = new HashMap<Integer, Integer>();
    }

    public void inc(Action action) {
        Integer id = action.getId();
        ensureAction(id);
        scoreList.get(indiceMap.get(id)).inc();
    }

    public List<ActionScore> getScoreList() {
        return scoreList;
    }

    public void dec(Action action) {
        Integer id = action.getId();
        ensureAction(id);
        scoreList.get(indiceMap.get(id)).dec();
    }

    protected void ensureAction(Integer id) {

        if (indiceMap.get(id) != null) {
            return;
        }
        ActionScore score = new ActionScore();
        score.setActionId(id);
        score.setActionScore(new Integer(0));
        scoreList.add(score);
        indiceMap.put(id, new Integer(scoreList.size() - 1));

    }

    public List<ActionScore> sort(){
        Collections.sort(scoreList, new Comparator<ActionScore>() {

            public int compare(ActionScore o1, ActionScore o2) {
                return -1 * (o1.getActionScore().compareTo(o2.getActionScore()));
            }
        });

        indiceMap.clear();
        //reorder indice map
        for (int i = 0; i < scoreList.size(); i++) {
            ActionScore score = scoreList.get(i);
            indiceMap.put(new Integer(i), score.getActionId());
        }

        return scoreList;
    }

    public  void removeAction(Integer actionId){
        if (indiceMap.get(actionId) == null) {
            return;
        }

        scoreList.remove(indiceMap.get(actionId));
        indiceMap.remove(actionId);
    }


    public void incAll(List<Action> actions){
        for (Action action : actions) {
            inc(action);
        }
    }

    public void decAll(List<Action> actions){
        for (Action action : actions) {
            dec(action);
        }
    }

    public boolean scoreIsNotEmpty(){
        return (scoreList.size() > 0);
    }

    public void print(){
        for(ActionScore model : scoreList) {
            System.out.println(model);
        }
    }

}
