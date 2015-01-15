package mydiaryweb.entity.localization.input.indoor;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author dplecan
 */
@Entity
@Table(name = "indoor_rooms")
public class Room implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_indoor_rooms_id_seq", sequenceName = "en_indoor_rooms_id_seq", allocationSize = 1)
    @JsonIgnore
    private Long id;

    @Basic(optional = false)
    @Column(name = "room_name", length = 1000)
    private String roomName;
    
    @OneToMany
    private List<Calibration> calibrations;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    private String locationName;
    private String locationAddress;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<Calibration> getCalibrations() {
        return calibrations;
    }

    public void setCalibrations(List<Calibration> calibrations) {
        this.calibrations = calibrations;
    }
}
