package mydiaryweb.dto.localization.kNN;


import mydiaryweb.entity.localization.input.indoor.Measure;
import mydiaryweb.entity.localization.input.indoor.Room;

/**
 * Created by Victor on 1/12/2015.
 */
public class IndoorResult {
    Room room;
    Measure measure;

    public IndoorResult(Room room, Measure measure) {
        this.room = room;
        this.measure = measure;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }
}
