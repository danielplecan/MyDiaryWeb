package mydiaryweb.controller;

import mydiaryweb.service.BehaviourService;
import mydiaryweb.service.InferenceService;
import mydiaryweb.service.MovementService;
import mydiaryweb.service.OutdoorLocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import mydiaryweb.module.behaviour.logic.BehaviourUtils;
import mydiaryweb.module.inferences.InferencesHandler;
import mydiaryweb.service.FaceService;
import mydiaryweb.service.SoundService;

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
    
    @Autowired
    private FaceService faceService;
    
    @Autowired
    private SoundService soundService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/start-processing")
    @ResponseBody
    public Map<String, Object> startProcessing() {
        Map<String, Object> responseMap = new HashMap<>();
        
        outdoorLocalizationService.launchProcessing();
        
        InferencesHandler.setService(inferenceService);
        InferencesHandler.StartInferencing(outdoorLocalizationService.getCurrentDayLocations(), 
                movementService.getCurrentDayMoves(), 
                faceService.getCurrentDayFaces(), 
                soundService.getCurrentDaySounds(), 
                120);
        
        BehaviourUtils.setServices(inferenceService, behaviourService);
        BehaviourUtils.processBehaviour(inferenceService.getCurrentDayActivities());
        
        return responseMap;
    }
}
