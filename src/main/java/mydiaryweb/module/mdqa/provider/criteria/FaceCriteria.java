package mydiaryweb.module.mdqa.provider.criteria;

import java.util.ArrayList;
import java.util.List;

import mydiaryweb.module.mdqa.model.Action;

public class FaceCriteria implements Criteria{

	@Override
	public List<Action> meetCriteria(List<Action> actions) {
		
		List<Action> faceActions = new ArrayList<Action>();
		for (Action action: actions){
			if(action.getFace() != null){
				faceActions.add(action);
			}
		}
		return faceActions;
	}

}
