package mydiaryweb.module.mdqa.provider.criteria;

import mydiaryweb.module.mdqa.model.Action;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class DateCriteria implements Criteria {

    DateTime date;

    int type;

    public DateCriteria(DateTime dt) {
        this.date = dt;
    }

    public void greater(){
        type = 1;
    }

    public void lower(){
        type = -1;
    }

    public void eq(){
        type = 0;
    }

    public boolean checkEqual(){
        return type == 0;
    }

    public boolean checkLower(){
        return type < 0;
    }

    public boolean checkGreater(){
        return type > 0;
    }

    @Override
    public List<Action> meetCriteria(List<Action> actions) {
        List<Action> thisDateActions = new ArrayList<Action>();
        for (Action action : actions) {
            boolean isFiltered = false;
            int compare = action.getActionDate().compareTo(date);
            if (checkEqual() && compare ==0){
                isFiltered = true;
            }
            if (checkLower() && compare < 0){
                isFiltered = true;
            }

            if (checkGreater() && compare > 0){
                isFiltered = true;
            }

            if(isFiltered){
                thisDateActions.add(action);
            }
        }

        return thisDateActions;
    }
}
