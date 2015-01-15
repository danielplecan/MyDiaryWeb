package mydiaryweb.module.mdqa.provider.criteria;

import mydiaryweb.module.mdqa.model.Action;
import mydiaryweb.module.mdqa.model.Face;

import java.util.ArrayList;
import java.util.List;

public class PersonCriteria implements Criteria {

    Face face;

    public PersonCriteria(String locData) {
        this.face = new Face();
        face.setName(locData);
    }

    @Override
    public List<Action> meetCriteria(List<Action> actions) {
        List<Action> facesMatched = new ArrayList<Action>();
        for (Action action : actions) {
            if (action.getFace() == null || action.getFace().getName() == null || action.getFace().getName().isEmpty()) {
                continue;
            }
            boolean samePerson = action.getFace().getName().toLowerCase().contains(face.getName().toLowerCase()) ||
                    face.getName().toLowerCase().contains(action.getFace().getName().toLowerCase());
            if (samePerson) {
                facesMatched.add(action);
            }
        }

        return facesMatched;
    }
}
