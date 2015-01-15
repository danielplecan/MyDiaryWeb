package mydiaryweb.service;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import mydiaryweb.entity.behaviour.output.Behaviour;
import mydiaryweb.entity.faces.output.Face;
import mydiaryweb.entity.inferences.Action;
import mydiaryweb.entity.inferences.Main;
import mydiaryweb.entity.localization.output.Location;
import mydiaryweb.entity.movement.output.Move;
import mydiaryweb.entity.sound.output.Sound;
import mydiaryweb.util.DateUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dplecan
 */
@Service
public class InferenceService {
    @PersistenceContext
    private EntityManager entityManager;
    
    public List<Main> getCurrentDayActivities() {
        Date currentDate = new Date();
        Date beginning = DateUtility.getBeginningOfDay(currentDate);
        Date end = DateUtility.getEndOfDay(currentDate);
        TypedQuery<Main> mainsByDate = entityManager.createNamedQuery(Main.FIND_BY_DATE, Main.class);
        mainsByDate.setParameter("beginning", beginning);
        mainsByDate.setParameter("end", end);
        
        return mainsByDate.getResultList();
    }
    
    public List<Main> getActivitiesByActionName(String name) {
        TypedQuery<Main> activitiesByActionName = entityManager.createNamedQuery(Main.FIND_BY_ACTION_NAME, Main.class);
        activitiesByActionName.setParameter("action_name", name);
        return activitiesByActionName.getResultList();
    }
    
    public Action getActionById(Long id) {
        return entityManager.find(Action.class, id);
    }
    
    @Transactional
    public void setBehaviourForAction(Long actionId, Behaviour newBehaviour) {
        Action action = getActionById(actionId);
        action.setBehaviour(newBehaviour);
        entityManager.merge(newBehaviour);
        entityManager.merge(action);
    }
    
    @Transactional
    public void addMain(Action action, Date moment) {
        Main main = new Main();
        main.setAction(action);
        main.setDate(moment);
        
        entityManager.persist(main);
    }

    public Action getAction(Location loc, Move move, Sound sound, Face face) {
        TypedQuery<Action> activities = entityManager.createNamedQuery(Action.FIND, Action.class);
        activities.setParameter("proximity", loc.getProximity());
        activities.setParameter("movement_type", move.getMovementType());
        return activities.getSingleResult();
    }

    public Action addAction(Location loc, Move move, Sound sound, Face face, String name) {
        Action action = new Action();
        action.setFace(face);
        action.setSound(sound);
        action.setMovement(move);
        action.setLocation(loc);
        action.setName(name);
        entityManager.persist(action);
        
        return action;
    }
}
