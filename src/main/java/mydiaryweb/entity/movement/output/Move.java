/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydiaryweb.entity.movement.output;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Lucas
 */
@Entity
public class Move implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "move_id_seq", sequenceName = "move_id_seq", allocationSize = 1)
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
