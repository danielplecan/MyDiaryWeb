package mydiaryweb.module.localization.indoor.kNN;

public class Vote implements Comparable<Vote>{
	Voter voter;
	double distance;
	
	public Vote(Voter voter, double distance) {
		super();
		this.voter = voter;
		this.distance = distance;
	}
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public int compareTo(Vote v) {
		 
		double d = v.getDistance();
		if (this.distance < d) {
			return -1;
		} else {
			return 1;
		}
	}	
}
