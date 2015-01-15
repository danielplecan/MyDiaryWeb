/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydiaryweb.service;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import mydiaryweb.entity.inferences.Main;
import mydiaryweb.entity.movement.output.Move;
import mydiaryweb.util.DateUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lucas
 */

@Service
public class MovementService {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public void addMove(Move move){
        entityManager.persist(move);
    }
    
    @Transactional
    public boolean removeMove(Move move){
        
        Move rMove = entityManager.find(Move.class, move);
        
        if(rMove == null)
            return false;
        
        entityManager.remove(rMove);
        
        return true;
    }
    
    public List<Move> getCurrentDayMoves() {
        Date currentDate = new Date();
        Date beginning = DateUtility.getBeginningOfDay(currentDate);
        Date end = DateUtility.getEndOfDay(currentDate);
        TypedQuery<Move> movesByDate = entityManager.createNamedQuery(Move.FIND_BY_DATE, Move.class);
        movesByDate.setParameter("beginning", beginning);
        movesByDate.setParameter("end", end);
        
        return movesByDate.getResultList();
    }
    
}
