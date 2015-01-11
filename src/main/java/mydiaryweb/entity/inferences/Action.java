package mydiaryweb.entity.inferences;

import mydiaryweb.entity.localization.output.Location;
import mydiaryweb.entity.movement.output.Move;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
