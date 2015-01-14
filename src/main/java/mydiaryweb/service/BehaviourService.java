package mydiaryweb.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mydiaryweb.entity.behaviour.output.Behaviour;
import org.springframework.stereotype.Service;

/**
 *
 * @author dplecan
 */
@Service
public class BehaviourService {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public void addBehaviour(Behaviour newBehaviour) {
        entityManager.persist(newBehaviour);
    }
    
    public void updateBehaviour(Behaviour updatedBehaviour) {
        entityManager.merge(updatedBehaviour);
    }
}
