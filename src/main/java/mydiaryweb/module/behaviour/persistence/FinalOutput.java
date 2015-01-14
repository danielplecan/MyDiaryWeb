package mydiaryweb.module.behaviour.persistence;
import java.io.Serializable;

public class FinalOutput implements Serializable {
	private String actionID;
	private String day;
	private String facesID;
	private String locationID;
	private String howRecurringByDay;
	private String howRecurringByPerson;
	private String howRecurringByLocation;

	public String getActionID() {
		return actionID;
	}

	public void setActionID(String actionID) {
		this.actionID = actionID;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getFacesID() {
		return facesID;
	}

	public void setFacesID(String facesID) {
		this.facesID = facesID;
	}

	public String getLocationID() {
		return locationID;
	}

	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}

	public String getHowRecurringByDay() {
		return howRecurringByDay;
	}

	public void setHowRecurringByDay(String howRecurringByDay) {
		this.howRecurringByDay = howRecurringByDay;
	}

	public String getHowRecurringByPerson() {
		return howRecurringByPerson;
	}

	public void setHowRecurringByPerson(String howRecurringByPerson) {
		this.howRecurringByPerson = howRecurringByPerson;
	}

	public String getHowRecurringByLocation() {
		return howRecurringByLocation;
	}

	public void setHowRecurringByLocation(String howRecurringByLocation) {
		this.howRecurringByLocation = howRecurringByLocation;
	}

	@Override
	public String toString() {
		return "FinalOutput [actionID=" + actionID + ", day=" + day
				+ ", facesID=" + facesID + ", locationID=" + locationID
				+ ", howRecurringByDay=" + howRecurringByDay
				+ ", howRecurringByPerson=" + howRecurringByPerson
				+ ", howRecurringByLocation=" + howRecurringByLocation + "]";
	}

}
