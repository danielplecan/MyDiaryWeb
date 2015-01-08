/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydiaryweb.dto.textgeneration;

/**
 *
 * @author Lucas
 */
public class GeneratedTextDTO {
    
    private final Long userId;
    private final String generatedText;
    
    public GeneratedTextDTO(Long uid, String text){       
        
        this.userId = uid;
        this.generatedText = text;
        
    }
    
    public Long getUserId(){
        return this.userId;
    }
    
    public String getGeneratedText(){
        return this.generatedText;
    }
    
}

