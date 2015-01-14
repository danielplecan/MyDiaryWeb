package mydiaryweb.module.behaviour.persistence;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import mydiaryweb.entity.behaviour.output.Behaviour;

public class ActionsInput implements Comparable<ActionsInput> {

	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        private Long actionId;
	// user for what I calculate the behavior
	private String action;
	private String location;
	// who participate to this action; ex: Alex
	private String withWho;
	private Date date;
	private Behaviour behavior=null;

	public ActionsInput(String action, String location, String withWho, Date date, Behaviour behaviour, Long actionId) {
		super();
		this.action = action;
		this.location = location;
		this.withWho = withWho;
		this.date = date;
                this.behavior = behaviour;
                this.actionId = actionId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWithWho() {
		return withWho;
	}

	public void setWithWho(String withWho) {
		this.withWho = withWho;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Behaviour getBehavior() {
		return behavior;
	}

	public void setBehavior(Behaviour behavior) {
		this.behavior = behavior;
	}

        public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}
        
	@Override
	public String toString() {
		return "Action [action=" + action
				+ ", location=" + location + ", withWho=" + withWho + ", date="
				+ dateFormatter.format(date) + ", behavior=" + behavior + "]";
	}

	public int compareTo(ActionsInput other) {
		int actionCompare = action.compareTo(other.action);
		int locationCompare = 0;
		int whoCompare = 0;
		if (actionCompare != 0) {
			return actionCompare;
		} else {
			locationCompare = location.compareTo(other.location);
			if (locationCompare != 0) {
				return locationCompare;
			} else {
				whoCompare = withWho.compareTo(other.withWho);
				if (whoCompare != 0) {
					return whoCompare;
				} else {
					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();
					cal1.setTime(date);
					cal2.setTime(other.date);
					int tmp = ((Integer) cal1.get(Calendar.HOUR))
							.compareTo((Integer) cal2.get(Calendar.HOUR));
					if (tmp != 0) {
						return tmp;
					} else {
						tmp = ((Integer) cal1.get(Calendar.YEAR))
								.compareTo((Integer) cal2.get(Calendar.YEAR));
						if (tmp != 0) {
							return tmp;
						} else {
							tmp = ((Integer) cal1.get(Calendar.MONTH))
									.compareTo((Integer) cal2
											.get(Calendar.MONTH));

							if (tmp != 0) {
								return tmp;
							} else {
								return ((Integer) cal1
										.get(Calendar.DAY_OF_MONTH))
										.compareTo((Integer) cal2
												.get(Calendar.DAY_OF_MONTH));
							}
						}
					}
				}
			}
		}
	}

}
