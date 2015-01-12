package mydiaryweb.entity.inferences;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Created by Spac on 10 Ian 2015.
 */
@Entity
@Table(name = "inferences_main")
public class Main implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "inferences_main_id_seq", sequenceName = "inferences_main_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Action action;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "main_date")
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
