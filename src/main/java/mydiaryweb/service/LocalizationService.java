package mydiaryweb.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mydiaryweb.entity.localization.output.Location;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel
 */
@Service
public class LocalizationService {
    @PersistenceContext
    private EntityManager entityManager;
    
    public void addLocations(List<Location> locations) {
        for(Location location : locations) {
            entityManager.persist(location);
        }
    }
}
