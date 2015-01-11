package mydiaryweb.entity.faces.output;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by PaulAlbert on 11.01.2015.
 */
@Entity
@Table(name = "faces")
public class Face implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "face_id_seq", sequenceName = "face_id_seq", allocationSize = 1)
    private Long id;

    @Basic(optional = false)
    @Column(name = "face_name", length = 1000)
    private String faceName;

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

}
