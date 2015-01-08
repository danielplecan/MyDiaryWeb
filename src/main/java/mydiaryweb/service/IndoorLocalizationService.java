package mydiaryweb.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mydiaryweb.dto.localization.IndoorLocationDTO;
import mydiaryweb.entity.localization.input.indoor.Measure;
import mydiaryweb.entity.localization.input.indoor.MeasureValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dplecan
 */
@Service
public class IndoorLocalizationService {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public void addIndoorLocation(IndoorLocationDTO indoorLocationDTO) {
        
    }
    
    @Transactional
    public void addMeasures(Measure measure) {
        List<MeasureValue> measureValues = measure.getMeasures();
        for(MeasureValue measureValue : measureValues) {
            entityManager.persist(measureValue);
        }
        
        entityManager.persist(measure);
    }
}
