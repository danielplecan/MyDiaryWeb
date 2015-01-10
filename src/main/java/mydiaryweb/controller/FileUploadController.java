package mydiaryweb.controller;

import java.util.HashMap;
import java.util.Map;
import mydiaryweb.dto.file.UploadedFileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Daniel
 */
@Controller
public class FileUploadController {
    @RequestMapping(method = RequestMethod.POST, value = "/api/upload")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestBody UploadedFileDTO uploadedFileDTO) {

        Map<String, Object> responseMap = new HashMap<>();
        
        responseMap.put("success", true);

        return responseMap;
    }
}
