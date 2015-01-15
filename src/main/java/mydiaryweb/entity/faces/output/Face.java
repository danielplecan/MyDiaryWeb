package mydiaryweb.entity.faces.output;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by PaulAlbert on 11.01.2015.
 */
@NamedQueries({
    @NamedQuery(name = Face.FIND_BY_DATE, query = "SELECT f FROM Face f WHERE f.timestamp >= :beginning AND f.timestamp <= :end"),
})
@Entity
@Table(name = "faces")
public class Face implements Serializable {
    public static final String FIND_BY_DATE = "Face.findByDate";
    
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
