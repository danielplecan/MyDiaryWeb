package mydiaryweb.entity.localization.input.indoor;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;

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
//    @JsonIgnore
    private List<Calibration> calibrations;

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
