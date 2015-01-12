package mydiaryweb.entity.faces.output;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by PaulAlbert on 11.01.2015.
 */
@Entity
@Table(name = "faces")
public class Face implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "face_id_seq", sequenceName = "face_id_seq", allocationSize = 1)
    private Long id;

    @Basic(optional = false)
    @Column(name = "face_name", length = 1000)
    private String faceName;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "face_timestamp")
    private Date timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFaceName() {
        return faceName;
    }

    public void setFaceName(String faceName) {
        this.faceName = faceName;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
