package mydiaryweb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mydiaryweb.dto.file.UploadedFileDTO;
import mydiaryweb.exception.FileUploadException;
import mydiaryweb.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private FileUploadService fileUploadService;
    
    @RequestMapping(method = RequestMethod.POST, value = "/api/upload")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestBody UploadedFileDTO uploadedFileDTO) {

        Map<String, Object> responseMap = new HashMap<>();
        
        try {
            fileUploadService.uploadFile(uploadedFileDTO);
            responseMap.put("success", true);
        } catch (FileUploadException | IOException ex) {
            Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
            responseMap.put("success", false);
        }
        

        return responseMap;
    }
}
