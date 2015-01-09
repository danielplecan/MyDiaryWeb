package mydiaryweb.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author dplecan
 */

@Controller
public class TextGenerationController {
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/text-generation/get-journal")
    @ResponseBody
    public Map<String, Object> getJournal() {
        Map<String, Object> responseMap = new HashMap<>();
        
        responseMap.put("journal", "JOURNAL");
        
        return responseMap;
    }
}

