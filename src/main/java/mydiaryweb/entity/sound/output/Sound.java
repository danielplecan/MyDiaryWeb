package mydiaryweb.entity.sound.output;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by PaulAlbert on 11.01.2015.
 */
@Entity
@Table(name = "sounds")
public class Sound implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "sound_id_seq", sequenceName = "sound_id_seq", allocationSize = 1)
    private Long id;

    @Basic(optional = false)
    @Column(name = "sound_name", length = 1000)
    private String soundName;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "sound_timestamp")
    private Date timestamp;

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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
