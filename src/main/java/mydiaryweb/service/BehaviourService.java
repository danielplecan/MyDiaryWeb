package mydiaryweb.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author dplecan
 */
@Service
public class BehaviourService {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    
}
