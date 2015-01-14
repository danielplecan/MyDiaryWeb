package mydiaryweb.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mydiaryweb.entity.behaviour.output.Behaviour;
import mydiaryweb.entity.inferences.Main;
import org.springframework.stereotype.Service;

/**
 *
 * @author dplecan
 */
@Service
public class InferenceService {
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<Main> getCurrentDayActivities() {
        List<Main> activities = new ArrayList<>();
        
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        
        return activities;
    }
    
    public List<Main> getActivitiesByActionName(String name) {
        List<Main> activities = new ArrayList<>();
        
        return activities;
    }
    
    public void setBehaviourForAction(Long actionId, Behaviour newBehaviour) {
        
    }
}
