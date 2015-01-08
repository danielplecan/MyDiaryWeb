/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydiaryweb.dto.movement;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Lucas
 */

public class MoveDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String movementType;

    private Date startMoment;
    

    public String getMovementType(){
        return movementType;
    }
    
    public Date getStartMoment(){
        return startMoment;
    }

    public void setMovementType(String type){
        this.movementType = type;
    }
    
    public void setStartMoment(Date start){
        this.startMoment = start;
    }
    
    
}
