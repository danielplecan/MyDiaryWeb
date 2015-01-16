package mydiaryweb.controller;

import mydiaryweb.entity.localization.output.Location;
import mydiaryweb.module.behaviour.logic.BehaviourUtils;
import mydiaryweb.module.inferences.InferencesHandler;
import mydiaryweb.module.localization.indoor.IndoorLocalization;
import mydiaryweb.module.soundvoice.VoiceRecognition;
import mydiaryweb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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
    private IndoorLocalizationService indoorLocalizationService;
    
    @Autowired
    private LocalizationService localizationService;
    
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
        
        List<Location> outdoorLocations = outdoorLocalizationService.launchProcessing();
        List<Location> indoorLocations = indoorLocalizationService.launchProcessing();
        List<Location> locations = IndoorLocalization.mergeIndoorWithOudoor(indoorLocations, outdoorLocations);
        localizationService.addLocations(locations);

        String soundName = VoiceRecognition.recognizeVoice(System.getProperty("catalina.home")
                + File.separator + "files" + File.separator + "voices" +
                File.separator + "s6.wav");
        
        soundService.addSound(soundName);
        
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
