package mydiaryweb.dto.localization;

import java.util.List;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author dplecan
 */
public class IndoorLocationDTO {
    @NotBlank
    private String locationName;
    
    private double latitude;
    
    private double longitude;
    
    private List<RoomDTO> rooms;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }
}
