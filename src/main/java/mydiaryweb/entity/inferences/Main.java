package mydiaryweb.entity.inferences;

import javax.persistence.*;

/**
 * Created by Spac on 10 Ian 2015.
 */
@Entity
@Table(name = "inferences_main")
public class Main {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "en_inferences_main_id_seq", sequenceName = "en_inferences_main_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private Action action;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
