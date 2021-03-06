package mydiaryweb.service;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import mydiaryweb.entity.faces.output.Face;
import mydiaryweb.entity.localization.output.Location;
import org.springframework.stereotype.Service;
import mydiaryweb.util.DateUtility;

/**
 *
 * @author dplecan
 */
@Service
public class FaceService {

    @PersistenceContext
    private EntityManager entityManager;

    
    public List<Face> getCurrentDayFaces() {
        Date currentDate = new Date();
        Date beginning = DateUtility.getBeginningOfDay(currentDate);
        Date end = DateUtility.getEndOfDay(currentDate);
        TypedQuery<Face> facesByDate = entityManager.createNamedQuery(Face.FIND_BY_DATE, 
                Face.class);
        facesByDate.setParameter("beginning", beginning);
        facesByDate.setParameter("end", end);
        
        return facesByDate.getResultList();
    }
    
    public void addFace(String faceName) {
        Face face = new Face();
        face.setFaceName(faceName);
        face.setTimestamp(new Date());
        
        entityManager.persist(face);
    }
}
