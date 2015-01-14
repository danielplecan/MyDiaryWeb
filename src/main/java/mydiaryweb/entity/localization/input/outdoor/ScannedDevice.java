package mydiaryweb.entity.localization.input.outdoor;

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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dplecan
 */

@NamedQueries({
    @NamedQuery(name = ScannedDevice.FIND_BY_TIMESTAMP, query = "SELECT sd from ScannedDevice sd WHERE sd.timeStamp = :timeStamp "),
})

@Entity
@Table(name = "outdoor_scanned_device")
public class ScannedDevice implements Serializable {
    
    public static final String FIND_BY_TIMESTAMP = "ScannedDevice.findByTimestamp";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "outdoor_scanned_device_id_seq", sequenceName = "outdoor_scanned_device_id_seq", allocationSize = 1)
    @JsonIgnore
    private Long id;

    @Basic(optional = false)
    @Column(name = "scanned_device_mac", length = 1000)
    private String macDevice;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "scanned_device_timestamp")
    private Date timeStamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMacDevice() {
        return macDevice;
    }

    public void setMacDevice (String macDevice){
        this.macDevice = macDevice;
    }
    
    public Date getTimeStamp(){
        return timeStamp;
    }
    
    public void setTimeStamp(Date timeStamp){
        this.timeStamp = timeStamp;
    }
}
