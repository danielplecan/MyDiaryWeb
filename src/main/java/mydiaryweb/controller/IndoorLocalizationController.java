package mydiaryweb.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import mydiaryweb.dto.localization.IndoorLocationDTO;
import mydiaryweb.entity.localization.input.indoor.Measure;
import mydiaryweb.service.IndoorLocalizationService;
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
public class IndoorLocalizationController {

    @Autowired
    private IndoorLocalizationService indoorLocalizationService;

    @RequestMapping(method = RequestMethod.POST, value = "/api/localization/indoor/add-location")
    @ResponseBody
    public Map<String, Object> addIndoorLocation(@RequestBody @Valid IndoorLocationDTO indoorLocationDTO,
            BindingResult bindingResult) {

        Map<String, Object> responseMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
        } else {
            indoorLocalizationService.addIndoorLocation(indoorLocationDTO);
            responseMap.put("success", true);
        }

        return responseMap;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/localization/indoor/add-measures")
    @ResponseBody
    public Map<String, Object> addMeasures(@RequestBody @Valid Measure measure,
            BindingResult bindingResult) {

        Map<String, Object> responseMap = new HashMap<>();
        if (bindingResult.hasErrors()) {
            responseMap.put("success", false);
        } else {
            indoorLocalizationService.addMeasures(measure);
            responseMap.put("success", true);
        }

        return responseMap;
    }
}
