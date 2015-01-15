package mydiaryweb.module.mdqa.provider.criteria;

import mydiaryweb.module.mdqa.model.Action;

import java.util.List;

public class OrCriteria implements Criteria{
    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Action> meetCriteria(List<Action> actions) {
        List<Action> firstCriteriaItems = criteria.meetCriteria(actions);
        List<Action> otherCriteriaItems = otherCriteria.meetCriteria(actions);

        for (Action action : otherCriteriaItems) {
            if(!firstCriteriaItems.contains(action)){
                firstCriteriaItems.add(action);
            }
        }
        return firstCriteriaItems;
    }
}
