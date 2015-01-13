package mydiaryweb.controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import mydiaryweb.entity.localization.input.outdoor.Device;
import mydiaryweb.entity.localization.input.outdoor.RegisteredOutdoorLocation;
import mydiaryweb.entity.localization.input.outdoor.ScannedDevice;
import mydiaryweb.entity.localization.input.outdoor.VisitedOutdoorLocation;
import mydiaryweb.service.OutdoorLocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OutdoorLocalizationController {
    @Autowired
    private OutdoorLocalizationService outdoorLocalizationService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/api/localization/outdoor/register-location")
    @ResponseBody
    public Map<String, Object> registerOutdoorLocation(@RequestBody @Valid RegisteredOutdoorLocation registeredOutdoorLocation,
            BindingResult bindingResult) {

        Map<String, Object> responseMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
        } else {
            outdoorLocalizationService.registerOutdoorLocation(registeredOutdoorLocation);
            responseMap.put("success", true);
        }

        return responseMap;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/api/localization/outdoor/add-visited-location")
    @ResponseBody
    public Map<String, Object> addVisitedLocation(@RequestBody @Valid VisitedOutdoorLocation visitedOutdoorLocation,
            BindingResult bindingResult) {

        Map<String, Object> responseMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
        } else {
            outdoorLocalizationService.addVisitedLocation(visitedOutdoorLocation);
            responseMap.put("success", true);
        }

        return responseMap;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/api/localization/outdoor/register-device")
    @ResponseBody
    public Map<String, Object> registerDevice(@RequestBody @Valid Device device,
            BindingResult bindingResult) {

        Map<String, Object> responseMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
        } else {
            outdoorLocalizationService.registerDevice(device);
            responseMap.put("success", true);
        }

        return responseMap;
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/api/localization/outdoor/add-scanned-device")
    @ResponseBody
    public Map<String, Object> addScannedDevice(@RequestBody @Valid ScannedDevice scannedDevice,
            BindingResult bindingResult) {

        Map<String, Object> responseMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
        } else {
            outdoorLocalizationService.addScannedDevice(scannedDevice);
            responseMap.put("success", true);
        }

        return responseMap;
    }
    
}
