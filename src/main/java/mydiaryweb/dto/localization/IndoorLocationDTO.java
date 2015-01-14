package mydiaryweb.dto.localization;

import mydiaryweb.entity.localization.input.indoor.IndoorLocation;
import mydiaryweb.entity.localization.input.indoor.Room;
import mydiaryweb.module.localization.outdoor.OutdoorLocalization;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

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
    
    public IndoorLocation getIndoorLocationFromDTO() {
        IndoorLocation indoorLocation = new IndoorLocation();
        
        List<Room> locationRooms = new ArrayList<>();
        for(RoomDTO roomDTO : rooms) {
            locationRooms.add(roomDTO.getRoomFromDTO());
        }
        
        indoorLocation.setLocationName(locationName);
        indoorLocation.setAddress(OutdoorLocalization.reverseGeocoding(latitude, longitude)); //Localization code for obtaining an adress from coordinates comes here
        indoorLocation.setRooms(locationRooms);
        
        return indoorLocation;
    }
}
