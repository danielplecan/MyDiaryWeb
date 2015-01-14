package mydiaryweb.module.behaviour.persistence;
import java.io.Serializable;

public class OutputDayObject  implements Serializable{
	private String actionID;
	private String day;
	private String who;
	private String where;
	private int times = 1;

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

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actionID == null) ? 0 : actionID.hashCode());
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((where == null) ? 0 : where.hashCode());
		result = prime * result + ((who == null) ? 0 : who.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OutputDayObject other = (OutputDayObject) obj;
		if (actionID == null) {
			if (other.actionID != null)
				return false;
		} else if (!actionID.equals(other.actionID))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (where == null) {
			if (other.where != null)
				return false;
		} else if (!where.equals(other.where))
			return false;
		if (who == null) {
			if (other.who != null)
				return false;
		} else if (!who.equals(other.who))
			return false;
		return true;
	}

}
// aceasta clasa va avea ca scop numararea actiunilor specifice
// (unde,cu cine, in ce zi a saptamanii),
// in urma caruia vom stabili daca o actiune este habit(>=90%),
// strong occurrence(>=80%), great probability(>=70%),
// generally done(>=60%), weak pattern(>=50%), irrelevant(0%-50%).
// Vom putea combina aceste atribute "day","who","where", pentru
// a scoate in evidenta si alte patternuri ulterioare, spre exemplu
// avem actionID=0074 ce corespunde mersului cu barca pe lacul din parc
// si daca analizam dupa who vom avea (90% Maria 10% George). De aici
// putem scoate un nou pattern -> userul merge cu Maria de obicei la actiunea
// 0074
// Astfel gasim activitatile recurente si incercam sa grupam cu localisationID
// si
// cu who(nu ambele odata, ci pe rand). In cazul gasirii de activitati recurente
// cu
// anumite persoane sau locuri, se vor da valori stringurilor. Altfel, NULL.

// ATENTIE
// Orice actiune se va lua in considerare daca suma(times) pt actionID
// va fi >8.