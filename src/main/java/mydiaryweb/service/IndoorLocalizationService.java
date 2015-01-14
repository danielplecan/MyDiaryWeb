package mydiaryweb.service;

import mydiaryweb.dto.localization.IndoorLocationDTO;
import mydiaryweb.entity.localization.input.indoor.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
            List<Calibration> calibrations = room.getCalibrations();
            for(Calibration calibration : calibrations) {
                List<CalibrationValue> calibrationValues = calibration.getCalibrationValues();
                for(CalibrationValue calibrationValue : calibrationValues) {
                    entityManager.persist(calibrationValue);
                }
                entityManager.persist(calibration);
            }
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
