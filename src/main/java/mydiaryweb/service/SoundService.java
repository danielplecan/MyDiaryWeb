package mydiaryweb.service;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import mydiaryweb.entity.sound.output.Sound;
import org.springframework.stereotype.Service;
import mydiaryweb.util.DateUtility;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dplecan
 */
@Service
public class SoundService {

    @PersistenceContext
    private EntityManager entityManager;

    
    public List<Sound> getCurrentDaySounds() {
        Date currentDate = new Date();
        Date beginning = DateUtility.getBeginningOfDay(currentDate);
        Date end = DateUtility.getEndOfDay(currentDate);
        TypedQuery<Sound> soundsByDate = entityManager.createNamedQuery(Sound.FIND_BY_DATE, 
                Sound.class);
        soundsByDate.setParameter("beginning", beginning);
        soundsByDate.setParameter("end", end);
        
        return soundsByDate.getResultList();
    }

    @Transactional
    public void addSound(String soundName) {
        Sound sound = new Sound();
        sound.setSoundName(soundName);
        sound.setTimestamp(new Date());
        
        entityManager.persist(sound);
    }
}
