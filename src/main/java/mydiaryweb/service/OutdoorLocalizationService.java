package mydiaryweb.service;

import mydiaryweb.entity.localization.input.outdoor.Device;
import mydiaryweb.entity.localization.input.outdoor.RegisteredOutdoorLocation;
import mydiaryweb.entity.localization.input.outdoor.ScannedDevice;
import mydiaryweb.entity.localization.input.outdoor.VisitedOutdoorLocation;
import mydiaryweb.entity.localization.output.Location;
import mydiaryweb.module.localization.outdoor.OutdoorLocalization;
import mydiaryweb.util.DateUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

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
    
    @Transactional
    public void registerDevice(Device device) {
        entityManager.persist(device);
    }
    
    @Transactional
    public void addScannedDevice(ScannedDevice scannedDevice) {
        entityManager.persist(scannedDevice);
    }
    
    @Transactional
    public List<RegisteredOutdoorLocation> getAllRegisteredLocations() {
        TypedQuery<RegisteredOutdoorLocation> registeredLocationsQuery = entityManager.createNamedQuery(RegisteredOutdoorLocation.FIND_ALL, 
                RegisteredOutdoorLocation.class);
        
        return registeredLocationsQuery.getResultList();
    }
    
    @Transactional
    public List<VisitedOutdoorLocation> getAllVisitedLocations() {
        TypedQuery<VisitedOutdoorLocation> visitedLocationsQuery = entityManager.createNamedQuery(VisitedOutdoorLocation.FIND_ALL, 
                VisitedOutdoorLocation.class);
        
        return visitedLocationsQuery.getResultList();
    }
    
    @Transactional 
    public List<Device> getAllRegisteredDevices() {
        TypedQuery<Device> registeredDevicesQuery = entityManager.createNamedQuery(Device.FIND_ALL, Device.class);
        
        return registeredDevicesQuery.getResultList();
    }
    
    @Transactional 
    public List<ScannedDevice> getScannedDevicesByTimestamp(Date checkTimeStamp) {
        TypedQuery<ScannedDevice> scannedDevicesByTimestamp = entityManager.createNamedQuery(ScannedDevice.FIND_BY_TIMESTAMP, ScannedDevice.class);
        scannedDevicesByTimestamp.setParameter("timeStamp", checkTimeStamp);
        return scannedDevicesByTimestamp.getResultList();
    }
    
    @Transactional
    public List<Location> launchProcessing() {
        List<Location> locations = OutdoorLocalization.locate(getAllRegisteredLocations(), getAllVisitedLocations());
        //deleteAllVisitedLocations();
        
        return locations;
    }
    
    public void launchDeviceVerification(Date timeStamp){
        OutdoorLocalization.verifyDevice(getAllRegisteredDevices(),getScannedDevicesByTimestamp(timeStamp));
    }
    
    @Transactional
    public void deleteAllVisitedLocations() {
        Query visitedLocationsQuery = entityManager.createNamedQuery(VisitedOutdoorLocation.DELETE_ALL);
        visitedLocationsQuery.executeUpdate();
    }
    
    public List<Location> getCurrentDayLocations() {
        Date currentDate = new Date();
        Date beginning = DateUtility.getBeginningOfDay(currentDate);
        Date end = DateUtility.getEndOfDay(currentDate);
        TypedQuery<Location> locationsByDate = entityManager.createNamedQuery(Location.FIND_BY_DATE, 
                Location.class);
        locationsByDate.setParameter("beginning", beginning);
        locationsByDate.setParameter("end", end);
        
        return locationsByDate.getResultList();
    }
}
