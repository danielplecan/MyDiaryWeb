package mydiaryweb.module.localization.indoor.kNN;


import mydiaryweb.entity.localization.input.indoor.Calibration;
import mydiaryweb.entity.localization.input.indoor.Room;

public class Voter {
	Room room;
	Calibration calibration;
	
	public Voter(Room room, Calibration calibration) {
		super();
		this.room = room;
		this.calibration = calibration;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Calibration getCalibration() {
		return calibration;
	}
	public void setCalibration(Calibration calibration) {
		this.calibration = calibration;
	}
}
