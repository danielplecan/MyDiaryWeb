package mydiaryweb.entity.behaviour.output;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dplecan
 */
@Entity
@Table(name = "behaviours")
public class Behaviour implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "behaviours_id_seq", sequenceName = "behaviours_id_seq", allocationSize = 1)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(optional = false)
    @Column(name = "next_time")
    private Date nextTime;
    
    @Basic(optional = false)
    @Column(name = "counter")
    private int counter;
    
    @Basic(optional = false)
    @Column(name = "flag")
    private boolean flag = false;
    

    public Behaviour(Date nextTime, int counter, boolean flag) {
        this.nextTime = new Date(nextTime.getTime());
        this.counter = counter;
        this.flag = flag;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
