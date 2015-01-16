package mydiaryweb.entity.localization.input.indoor;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author dplecan
 */
@NamedQueries({
    @NamedQuery(name = IndoorLocation.FIND_ALL, query = "SELECT il FROM IndoorLocation il"),
})
@Entity
@Table(name = "indoor_locations")
public class IndoorLocation implements Serializable {
    public static final String FIND_ALL = "IndoorLocation.findAll";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_indoor_locations_id_seq", sequenceName = "en_indoor_locations_id_seq", allocationSize = 1)
    private Long id;

    @Basic(optional = false)
    @Column(name = "location_name", length = 1000)
    private String locationName;
    
    @Basic(optional = false)
    @Column(name = "location_address", length = 1000)
    private String address;
    
    @OneToMany
    private List<Room> rooms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
