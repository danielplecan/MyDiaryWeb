package mydiaryweb.entity.localization.output;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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

    public static List<Location> mergeIndoorWithOudoor(
            List<Location> indoorList,
            List<Location> outdoorList
    ) {
        List<Location> filteredOutdoorList = new ArrayList<>();

        for (Location indoor : indoorList) {
            for (Location outdoor : outdoorList) {
                if(Math.abs(
                        indoor.getTimestamp().getTime()
                                - outdoor.getTimestamp().getTime())
                        > 5 * 1000) {
                    filteredOutdoorList.add(outdoor);
                }
            }
        }

        List<Location> finalList = new ArrayList<>();
        finalList.addAll(indoorList);
        finalList.addAll(filteredOutdoorList);
        Collections.sort(finalList, new Comparator<Location>() {

            @Override
            public int compare(Location o1, Location o2) {
                if (o1.getTimestamp().getTime() > o2.getTimestamp().getTime())
                    return 1;
                else if (o1.getTimestamp().getTime() < o2.getTimestamp().getTime())
                    return -1;
                return 0;
            }

        });
        return finalList;
    }
}
