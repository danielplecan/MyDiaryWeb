package mydiaryweb.module.mdqa.provider.criteria;


import mydiaryweb.module.mdqa.model.Action;

import java.util.ArrayList;
import java.util.List;

public class UIDCriteria implements Criteria{

    int id;

    public UIDCriteria(int id) {
        this.id = id;
    }

    @Override
    public List<Action> meetCriteria(List<Action> actions) {
        List<Action> thisUIDActions = new ArrayList<Action>();
        for (Action action : actions) {
            if (action.getId() == id ) {
                thisUIDActions.add(action);
            }
        }

        return thisUIDActions;
    }
}
