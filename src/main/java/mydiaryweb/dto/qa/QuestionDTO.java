package mydiaryweb.dto.qa;

import java.io.Serializable;

/**
 *
 * @author Lucas
 */

public class QuestionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String questionText;
    
    public Long getId() {
        return id;
    }

    public String getQuestionText(){
        return this.questionText;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setQuestionText(String generatedQuestionText){
        
        this.questionText = generatedQuestionText;
        
    }
    
}
