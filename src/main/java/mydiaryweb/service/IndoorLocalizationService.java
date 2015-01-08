package mydiaryweb.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mydiaryweb.dto.localization.IndoorLocationDTO;
import mydiaryweb.entity.localization.input.indoor.IndoorLocation;
import mydiaryweb.entity.localization.input.indoor.Measure;
import mydiaryweb.entity.localization.input.indoor.MeasureValue;
import mydiaryweb.entity.localization.input.indoor.Room;
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
        IndoorLocation indoorLocation = indoorLocationDTO.getIndoorLocationFromDTO();
        List<Room> rooms = indoorLocation.getRooms();
        
        for(Room room : rooms) {
            entityManager.persist(room);
        }
        
        entityManager.persist(indoorLocation);
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
