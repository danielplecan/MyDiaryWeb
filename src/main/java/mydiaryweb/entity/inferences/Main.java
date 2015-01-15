package mydiaryweb.entity.inferences;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Created by Spac on 10 Ian 2015.
 */
@NamedQueries({
    @NamedQuery(name = Main.FIND_BY_DATE, query = "SELECT m FROM Main m WHERE m.date >= :beginning AND m.date <= :end"),
    @NamedQuery(name = Main.FIND_BY_ACTION_NAME, 
            query = "SELECT m FROM Main m WHERE m.action.name = :action_name")
})
@Entity
@Table(name = "inferences_main")
public class Main implements Serializable {
    public static final String FIND_BY_DATE = "Main.findByDate";
    public static final String FIND_BY_ACTION_NAME = "Main.findByActionName";
    
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
