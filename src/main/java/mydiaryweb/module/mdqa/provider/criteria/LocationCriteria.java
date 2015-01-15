package mydiaryweb.module.mdqa.provider.criteria;

import mydiaryweb.module.mdqa.model.Action;
import mydiaryweb.module.mdqa.model.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationCriteria implements Criteria {

    Location location;

    public LocationCriteria(String locData) {
        this.location = new Location();

        //might not now face type set both for now
        //TODO later it may be implemented different constructors on off switches to influence filtering
        location.setLocation(locData);
        location.setProximity(locData);
    }

    @Override
    public List<Action> meetCriteria(List<Action> actions) {
        List<Action> thisLocationActions = new ArrayList<Action>();
        for (Action action : actions) {
            boolean sameLoc, sameProx;
            sameLoc = action.getLocation().getLocation().toLowerCase().contains(location.getLocation().toLowerCase());
            sameProx = action.getLocation().getProximity().toLowerCase().contains(location.getProximity().toLowerCase());
            if (sameLoc || sameProx) {
                thisLocationActions.add(action);
            }
        }

        return thisLocationActions;
    }
}
