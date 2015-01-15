package mydiaryweb.module.mdqa.provider.criteria;

import mydiaryweb.module.mdqa.model.Action;

import java.util.List;

// Criteria Design Pattern implementation
public interface Criteria {
    public List<Action> meetCriteria(List<Action> actions);

}
