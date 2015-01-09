package mydiaryweb.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import mydiaryweb.dto.movement.MoveDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author dplecan
 */
@Controller
public class MovementController {
    @RequestMapping(method = RequestMethod.POST, value = "/api/movement/add-movevement-type")
    @ResponseBody
    public boolean addMove(@RequestBody @Valid MoveDTO moveDTO, 
            BindingResult bindingResult) {  
        
        return true;
        
    }
}

