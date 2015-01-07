package mydiaryweb.controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import mydiaryweb.dto.localization.IndoorLocationDTO;
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
    @RequestMapping(method = RequestMethod.POST, value = "/api/indoor/add-location")
    @ResponseBody
    public Map<String, Object> addIndoorLocation(@RequestBody @Valid IndoorLocationDTO indoorLocationDTO, 
            BindingResult bindingResult) {

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", true);

        return responseMap;
    }
}
