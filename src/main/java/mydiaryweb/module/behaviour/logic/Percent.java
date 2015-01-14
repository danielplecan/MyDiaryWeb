package mydiaryweb.module.behaviour.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import mydiaryweb.entity.behaviour.output.Behaviour;
import mydiaryweb.module.behaviour.persistence.ActionsInput;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;


public class Percent {

	private ArrayList<ActionsInput> actions = new ArrayList<ActionsInput>();

	public Percent(ArrayList<ActionsInput> actions,
			String currentAction) {
		super();
		this.actions = actions;
		for (int i = 0; i < actions.size(); i++) {
			if (!actions.get(i).getAction().equals(currentAction)) {
				actions.remove(i);
				i--;
			}
		}
		// Collections.shuffle(actions);
		Collections.sort(actions);
		System.out.println("\n\n");
		for (ActionsInput action : actions) {
			System.out.println(action);
		}
		createPatterns();
		System.out.println("\n\n");
		for (ActionsInput action : actions) {
			System.out.println(action);
		}
	}

	private void createPatterns() {
		String tmpLocation = "";
		String tmpWho = "";
		Date tmpDate = null;
		long tmpDifferenceDate = 0;
		int tmpCount = 0;
		for (int i = 0; i < actions.size(); i++) {
			ActionsInput action = actions.get(i);
			if (tmpCount == 0) {
				tmpLocation = action.getLocation();
				tmpWho = action.getWithWho();
				tmpDate = action.getDate();
				if (i != actions.size() - 1) {
					DateTime dt1 = new DateTime(tmpDate);
					DateTime dt2 = new DateTime(actions.get(i + 1).getDate());
					tmpDifferenceDate = Days.daysBetween(dt1, dt2).getDays();
				}
				tmpCount++;
			} else {
				DateTime dt1 = new DateTime(tmpDate);
				DateTime dt2 = new DateTime(action.getDate());
				if (action.getLocation().equals(tmpLocation)
						&& action.getWithWho().equals(tmpWho)
						&& dt1.getHourOfDay() == dt2.getHourOfDay()
						&& (Days.daysBetween(dt1, dt2).getDays() == tmpDifferenceDate
								|| Days.daysBetween(dt1, dt2).getDays() == tmpDifferenceDate - 1 || Days
								.daysBetween(dt1, dt2).getDays() == tmpDifferenceDate + 1)) {
					tmpCount++;
					tmpDate = action.getDate();
				} else {
					if (tmpCount >= 3) {
						Date dateAfter = DateUtils.addDays(tmpDate,
								(int) tmpDifferenceDate);
						Behaviour b = new Behaviour(dateAfter, tmpCount, false);
						for (int j = i - 1; j >= i - tmpCount; j--)
							actions.get(j).setBehavior(b);
					}
					tmpCount = 0;
					i--;
				}
			}
		}
	}
}
