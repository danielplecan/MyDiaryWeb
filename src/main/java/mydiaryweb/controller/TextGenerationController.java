package mydiaryweb.controller;

import mydiaryweb.dto.textgeneration.GeneratedTextDTO;
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
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/textgen/getjournal")
    @ResponseBody
    
    public GeneratedTextDTO getGenText() {

        Long id = new Long("1241421412");
        String generatedText = "Look at me, I am so super dandy and relaxed !";
        
        return new GeneratedTextDTO(id, generatedText);
 
    }
}

