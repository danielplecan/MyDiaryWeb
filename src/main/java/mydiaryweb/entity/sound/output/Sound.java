package mydiaryweb.entity.sound.output;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by PaulAlbert on 11.01.2015.
 */
@Entity
@Table(name = "sounds")
public class Sound implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "face_id_seq", sequenceName = "face_id_seq", allocationSize = 1)
    private Long id;

    @Basic(optional = false)
    @Column(name = "sound_name", length = 1000)
    private String soundName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }
}
