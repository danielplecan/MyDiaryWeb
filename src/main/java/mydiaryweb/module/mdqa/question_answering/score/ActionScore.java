package mydiaryweb.module.mdqa.question_answering.score;

/**
 * Created by Litechip on 12/27/2014.
 */
public class ActionScore {
    public Integer actionScore;
    public Integer actionId;

    public Integer getActionScore() {
        return actionScore;
    }

    public void setActionScore(Integer actionScore) {
        this.actionScore = actionScore;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public void inc(){
        this.actionScore = new Integer(this.actionScore.intValue() + 1);
    }

    public void dec(){
        this.actionScore = new Integer(this.actionScore.intValue() - 1);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionScore that = (ActionScore) o;

        if (actionId != null ? !actionId.equals(that.actionId) : that.actionId != null) return false;
        if (actionScore != null ? !actionScore.equals(that.actionScore) : that.actionScore != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actionScore != null ? actionScore.hashCode() : 0;
        result = 31 * result + (actionId != null ? actionId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return actionId + ":" + actionScore;

    }

}
