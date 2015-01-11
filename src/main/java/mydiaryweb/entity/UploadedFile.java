package mydiaryweb.entity;

/**
 *
 * @author Daniel
 */
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author dplecan
 */
@Entity
@Table(name = "uploads")
public class UploadedFile implements Serializable {
    public static final String LOCATION = System.getProperty("catalina.home") + File.separator + "uploads";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "uploads_id_seq", sequenceName = "uploads_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;
    
    @Column(name = "mime_type", nullable = false)
    @NotBlank
    private String mimeType;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "location_timestamp")
    private Date timestamp;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimestamp() {
        return new Date(timestamp.getTime());
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = new Date(timestamp.getTime());
    }
}
