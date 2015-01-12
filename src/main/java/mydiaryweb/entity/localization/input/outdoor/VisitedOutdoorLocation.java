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

/**
 *
 * @author dplecan
 */
@NamedQueries({
    @NamedQuery(name = VisitedOutdoorLocation.FIND_ALL, query = "SELECT u FROM VisitedOutdoorLocation u"),
    @NamedQuery(name = VisitedOutdoorLocation.DELETE_ALL, query = "DELETE FROM VisitedOutdoorLocation u")
})

@Entity
@Table(name = "visited_outdoor_locations")
public class VisitedOutdoorLocation implements Serializable {
    public static final String FIND_ALL = "VisitedOutdoorLocation.findAll";
    public static final String DELETE_ALL = "VisitedOutdoorLocation.deleteAll";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_visited_outdoor_locations_id_seq", sequenceName = "en_visited_outdoor_locations_id_seq", allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "location_latitude")
    private double latitude;
    
    @Basic(optional = false)
    @Column(name = "location_longitude")
    private double longitude;
    
    @Basic(optional = false)
    @Column(name = "location_timestamp", length = 1000)
    private String timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
