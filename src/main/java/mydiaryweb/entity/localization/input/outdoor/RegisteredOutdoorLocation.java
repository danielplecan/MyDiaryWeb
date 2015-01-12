package mydiaryweb.entity.localization.input.outdoor;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dplecan
 */
@NamedQueries({
    @NamedQuery(name = RegisteredOutdoorLocation.FIND_ALL, query = "SELECT u from RegisteredOutdoorLocation u"),
})
@Entity
@Table(name = "registered_outdoor_locations")
public class RegisteredOutdoorLocation implements Serializable {
    public static final String FIND_ALL = "RegisteredOutdoorLocation.findAll";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_registered_outdoor_locations_id_seq", sequenceName = "en_registered_outdoor_locations_id_seq", allocationSize = 1)
    @JsonIgnore
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "location_name", length = 1000)
    private String locationName;
    
    @Basic(optional = false)
    @Column(name = "location_latitude")
    private double latitude;
    
    @Basic(optional = false)
    @Column(name = "location_longitude")
    private double longitude;

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
}
