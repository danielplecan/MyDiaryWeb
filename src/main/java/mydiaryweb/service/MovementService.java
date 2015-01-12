/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydiaryweb.service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mydiaryweb.entity.movement.output.Move;
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
    
}
