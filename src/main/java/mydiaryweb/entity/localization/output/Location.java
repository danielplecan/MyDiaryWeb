package mydiaryweb.entity.localization.output;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
    @NamedQuery(name = Location.FIND_BY_DATE, query = "SELECT l FROM Location l WHERE l.timestamp >= :beginning AND l.timestamp <= :end"),
})
@Entity
@Table(name = "locations")
public class Location implements Serializable{
    public static final String FIND_BY_DATE = "Location.findByDate";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_locations_id_seq", sequenceName = "en_locations_id_seq", allocationSize = 1)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "location_timestamp")
    private Date timestamp;
    
    @Basic(optional = false)
    @Column(name = "location_type", length = 1000)
    private String type;
    
    @Basic(optional = false)
    @Column(name = "location_address", length = 1000)
    private String address;
    
    @Basic(optional = true)
    @Column(name = "location_proximity", length = 1000)
    private String proximity;
    
    @Basic(optional = false)
    @Column(name = "location_details", length = 1000)
    private String details;
    
    @Basic(optional = true)
    @Column(name = "location_vehicle_name", length = 1000)
    private String vehicleName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProximity() {
        return proximity;
    }

    public void setProximity(String proximity) {
        this.proximity = proximity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
