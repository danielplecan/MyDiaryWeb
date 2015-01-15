package mydiaryweb.entity.movement.output;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Lucas
 */
@NamedQueries({
    @NamedQuery(name = Move.FIND_BY_DATE, query = "SELECT m FROM Move m WHERE m.startMoment >= :beginning AND m.startMoment <= :end"),
})
@Entity
@Table(name = "moves")
public class Move implements Serializable {
    public static final String FIND_BY_DATE = "Move.findByDate";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "move_id_seq", sequenceName = "move_id_seq", allocationSize = 1)
    @JsonIgnore
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "movement_type", length = 1000)
    private String movementType;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "start_moment")
    private Date startMoment;

    public Long getId() {
        return id;
    }
    
    public String getMovementType(){
        return movementType;
    }
    
    public Date getStartMoment(){
        return startMoment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMovementType(String type){
        this.movementType = type;
    }
    
    public void setStartMoment(Date start){
        this.startMoment = start;
    }
    
}
