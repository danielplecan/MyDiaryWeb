package mydiaryweb.controller;

import java.util.HashMap;
import java.util.Map;
import mydiaryweb.dto.qa.QuestionDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import mydiaryweb.module.mdqa.Boostrap;
import mydiaryweb.service.InferenceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dplecan
 */

@Controller
public class QuestionAnsweringController {
    @Autowired
    private InferenceService inferenceService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/qa/get-initial-question")
    @ResponseBody
    public Map<String, Object> getInitialQuestion() {
        Map<String, Object> responseMap = new HashMap<>();
        
        responseMap.put("question", "INITIAL QUESTION");
        
        return responseMap;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/api/qa/get-response") //nu merge cu GET
    @ResponseBody
    public Map<String, Object> getResponse(@RequestBody QuestionDTO questionDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        
        Boostrap.inst().setData(inferenceService.getCurrentDayActivities());
        responseMap.put("response", Boostrap.inst().processResponse(questionDTO.getQuestion()));
        
        return responseMap;
    }
}

