package mydiaryweb.controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import mydiaryweb.dto.movement.MoveDTO;
import mydiaryweb.entity.localization.input.outdoor.Device;
import mydiaryweb.entity.movement.output.Move;
import mydiaryweb.service.MovementService;
import mydiaryweb.service.OutdoorLocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private MovementService movementService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/api/movement/add-movevement-type")
    @ResponseBody
    public Map<String, Object> addMovementType(@RequestBody @Valid Move move,
            BindingResult bindingResult) {

        Map<String, Object> responseMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
        } else {
            movementService.addMove(move);
            responseMap.put("success", true);
        }

        return responseMap;
    }
}

