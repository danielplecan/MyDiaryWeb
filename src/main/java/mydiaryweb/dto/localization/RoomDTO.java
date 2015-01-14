package mydiaryweb.dto.localization;

import mydiaryweb.entity.localization.input.indoor.Calibration;
import mydiaryweb.entity.localization.input.indoor.CalibrationValue;
import mydiaryweb.entity.localization.input.indoor.Room;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dplecan
 */
public class RoomDTO {
    @NotBlank
    private String roomName;

    private List<List<CalibrationValue>> calibrations;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<List<CalibrationValue>> getCalibrations() {
        return calibrations;
    }

    public void setCalibrations(List<List<CalibrationValue>> calibrations) {
        this.calibrations = calibrations;
    }

    public Room getRoomFromDTO() {
        Room room = new Room();

        room.setRoomName(roomName);
        List<Calibration> calibrationsList = new ArrayList<>();

        for(List<CalibrationValue> calibrationValues : calibrations) {
                Calibration calibration = new Calibration();
                calibration.setCalibrationValues(calibrationValues);
                calibrationsList.add(calibration);
        }
        room.setCalibrations(calibrationsList);
        return room;
    }
}
