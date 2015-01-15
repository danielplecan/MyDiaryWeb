package mydiaryweb.controller;

import java.util.HashMap;
import java.util.Map;
import mydiaryweb.module.behaviour.logic.BehaviourUtils;
import mydiaryweb.module.mdqa.model.Inference;
import mydiaryweb.service.BehaviourService;
import mydiaryweb.service.InferenceService;
import mydiaryweb.service.OutdoorLocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/start-processing")
    @ResponseBody
    public Map<String, Object> startProcessing() {
        Map<String, Object> responseMap = new HashMap<>();
        
        outdoorLocalizationService.launchProcessing();
        
        BehaviourUtils.setServices(inferenceService, behaviourService);
        BehaviourUtils.processBehaviour(inferenceService.getCurrentDayActivities());
        
        responseMap.put("success", true);
        
        return responseMap;
    }
}
