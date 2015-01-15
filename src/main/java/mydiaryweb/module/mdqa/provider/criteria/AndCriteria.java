package mydiaryweb.module.mdqa.provider.criteria;

import mydiaryweb.module.mdqa.model.Action;

import java.util.List;

public class AndCriteria implements Criteria{
    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Action> meetCriteria(List<Action> actions) {
        List<Action> firstCriteriaActions= criteria.meetCriteria(actions);
        return otherCriteria.meetCriteria(firstCriteriaActions);
    }
}
