package mydiaryweb.service;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mydiaryweb.dto.file.UploadedFileDTO;
import mydiaryweb.entity.UploadedFile;
import mydiaryweb.exception.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dplecan
 */
@Service
public class FileUploadService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Long uploadFile(UploadedFileDTO uploadedFileDTO) throws FileUploadException, IOException {
        saveFile(uploadedFileDTO);
        return persistFileEntity(uploadedFileDTO);
    }

    private void saveFile(UploadedFileDTO uploadedFileDTO) throws FileUploadException, IOException {
        if (uploadedFileDTO.getContent().isEmpty()) {
            throw new FileUploadException("Uploading has failed. The file was empty.");
        }

        String directoryPath = UploadedFile.LOCATION;
        File directory = new File(directoryPath);

        if ((!directory.exists()) && (!directory.mkdirs())) {
            throw new FileUploadException("Directories creation has failed. Check privileges.");
        }

        File serverFile = new File(directory.getAbsolutePath() + File.separator + uploadedFileDTO.getName());
        try (FileOutputStream outputStream = new FileOutputStream(serverFile)) {
            outputStream.write(Base64.decode(uploadedFileDTO.getContent()));
            outputStream.close();
        }
    }

    @Transactional
    private Long persistFileEntity(UploadedFileDTO uploadedFileDTO) {
        UploadedFile uploadedFile = new UploadedFile();

        uploadedFile.setName(uploadedFileDTO.getName());
        uploadedFile.setMimeType(uploadedFileDTO.getMimeType());
        uploadedFile.setTimestamp(uploadedFileDTO.getTimestamp());

        entityManager.persist(uploadedFile);

        return uploadedFile.getId();
    }

    @Transactional
    public boolean removeFile(Long fileId) {
        UploadedFile uploadedFile = entityManager.find(UploadedFile.class, fileId);
        if (uploadedFile == null) {
            return false;
        }

        File file = new File(UploadedFile.LOCATION + File.separator + uploadedFile.getName());

        if (!file.delete()) {
            return false;
        }

        entityManager.remove(uploadedFile);

        return true;
    }

    @Transactional
    public UploadedFile getUploadedFileEntity(Long fileId) {
        return entityManager.find(UploadedFile.class, fileId);
    }

    public File getFile(UploadedFile uploadedFile) {
        return new File(UploadedFile.LOCATION + File.separator + uploadedFile.getName());
    }
}
