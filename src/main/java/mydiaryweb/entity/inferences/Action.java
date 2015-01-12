package mydiaryweb.entity.inferences;

import java.io.Serializable;
import mydiaryweb.entity.faces.output.Face;
import mydiaryweb.entity.localization.output.Location;
import mydiaryweb.entity.movement.output.Move;
import mydiaryweb.entity.sound.output.Sound;

import javax.persistence.*;
import mydiaryweb.entity.behaviour.output.Behaviour;

/**
 * Created by Spac on 10 Ian 2015.
 *
 * action : id movement location face voice behaviour
 */
@Entity
@Table(name = "inferences_action")
public class Action implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "inferences_action_id_seq", sequenceName = "inferences_action_id_seq", allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "action_name", length = 1000)
    private String name;

    @ManyToOne
    @Basic(optional = false)
    private Move movement;

    @ManyToOne
    @Basic(optional = false)
    private Location location;

    @ManyToOne
    private Face face;

    @ManyToOne
    private Sound sound;

    @OneToOne
    private Behaviour behaviour;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }
}
