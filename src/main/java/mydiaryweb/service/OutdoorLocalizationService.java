package mydiaryweb.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mydiaryweb.entity.localization.input.outdoor.RegisteredOutdoorLocation;
import mydiaryweb.entity.localization.input.outdoor.VisitedOutdoorLocation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dplecan
 */
@Service
public class OutdoorLocalizationService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void registerOutdoorLocation(RegisteredOutdoorLocation registeredOutdoorLocation) {
        entityManager.persist(registeredOutdoorLocation);
    }

    @Transactional
    public void addVisitedLocation(VisitedOutdoorLocation visitedOutdoorLocation) {
        entityManager.persist(visitedOutdoorLocation);
    }
}
