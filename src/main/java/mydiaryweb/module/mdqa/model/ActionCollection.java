package mydiaryweb.module.mdqa.model;


import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionCollection {
    private Map<Integer, Action> actions = new HashMap<Integer, Action>();

    public List<Action> getActions() {
        return new ArrayList<Action>(actions.values());
    }

    public void setActions(List<Action> dayActions) {
        actions.clear();
        for (Action action : dayActions){
            actions.put(action.getId(), action);
        }
    }

    public DateTime getDateForCollection(){
        //TODO
        // If the action collection doe not contain actions for the same day add new logic
        if (actions.size() > 0){
            Map.Entry<Integer, Action> entry = actions.entrySet().iterator().next();
            return entry.getValue().getActionDate().getUTCDate();
        }

        return null;
    }
}
