package mydiaryweb.controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import mydiaryweb.dto.localization.IndoorLocationDTO;
import mydiaryweb.dto.qa.QuestionDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author dplecan
 */

@Controller
public class QuestionAnsweringController {
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/qa/getquestion")
    @ResponseBody
    
    public QuestionDTO getQuestion() {

        QuestionDTO question = new QuestionDTO();
        question.setQuestionText("Que esta pasando muchacho ?");
        
        return question;
 
    }
}

