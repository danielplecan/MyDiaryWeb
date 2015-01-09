package mydiaryweb.dto.movement;

import java.util.Date;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Lucas
 */

public class MoveDTO {
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
