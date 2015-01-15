package mydiaryweb.controller;

import mydiaryweb.entity.movement.output.Move;
import mydiaryweb.service.BehaviourService;
import mydiaryweb.service.InferenceService;
import mydiaryweb.service.MovementService;
import mydiaryweb.service.OutdoorLocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Daniel
 */
@Controller
public class ProcessingController {
    @Autowired
    private OutdoorLocalizationService outdoorLocalizationService;
    
    @Autowired
    private InferenceService inferenceService;
    
    @Autowired
    private BehaviourService behaviourService;

    @Autowired
    private MovementService movementService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/start-processing")
    @ResponseBody
    public Map<String, Object> startProcessing() {
        Map<String, Object> responseMap = new HashMap<>();
        
        /*outdoorLocalizationService.launchProcessing();
        
        BehaviourUtils.setServices(inferenceService, behaviourService);
        BehaviourUtils.processBehaviour(inferenceService.getCurrentDayActivities());
        
        responseMap.put("success", true);*/
        Move s = new Move();
        s.setMovementType("in_vehicle");
        s.setStartMoment(new Date());
        movementService.addMove(s);

        movementService.getCurrentDayMoves();

        
        return responseMap;
    }
}
