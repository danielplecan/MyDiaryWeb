package mydiaryweb.entity.localization.input.outdoor;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dplecan
 */


@NamedQueries({
    @NamedQuery(name = Device.FIND_ALL, query = "SELECT d from Device d"),
})
@Entity
@Table(name = "indoor_device")
public class Device implements Serializable {
    
    public static final String FIND_ALL = "ScannedDevice.findAll";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_indoor_device_id_seq", sequenceName = "en_indoor_device_id_seq", allocationSize = 1)
    @JsonIgnore
    private Long id;

    @Basic(optional = false)
    @Column(name = "device_name", length = 1000)
    private String nameDevice;
    
    @Basic(optional = false)
    @Column(name = "device_mac", length = 1000)
    private String mac;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice (String nameDevice){
        this.nameDevice = nameDevice;
    }
    
    public String getMac(){
        return mac;
    }
    
    public void setMac(String mac){
        this.mac = mac;
    }
    
}
