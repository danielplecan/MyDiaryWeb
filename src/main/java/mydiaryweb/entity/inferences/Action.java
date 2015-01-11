package mydiaryweb.entity.inferences;

import mydiaryweb.entity.faces.output.Face;
import mydiaryweb.entity.localization.output.Location;
import mydiaryweb.entity.movement.output.Move;
import mydiaryweb.entity.sound.output.Sound;

import javax.persistence.*;

/**
 * Created by Spac on 10 Ian 2015.
 *
 * action : id movement location face voice behaviour
 */
@Entity
@Table(name = "inferences_action")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_inferences_action_id_seq", sequenceName = "en_inferences_action_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private Move movement;

    @OneToOne
    private Location location;

    @OneToOne
    private Face face;

    @OneToOne
    private Sound sound;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Move getMovement() {
        return movement;
    }

    public void setMovement(Move movement) {
        this.movement = movement;
    }
}
