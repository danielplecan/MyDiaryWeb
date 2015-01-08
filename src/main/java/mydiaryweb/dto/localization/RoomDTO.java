package mydiaryweb.dto.localization;

import java.util.List;
import mydiaryweb.entity.localization.input.indoor.CalibrationValue;
import mydiaryweb.entity.localization.input.indoor.Room;
import org.hibernate.validator.constraints.NotBlank;

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
        
        for(List<CalibrationValue> calibrationValues : calibrations) {
            for(CalibrationValue calibrationValue : calibrationValues) {
                
            }
        }
        return new Room();
    }
}
