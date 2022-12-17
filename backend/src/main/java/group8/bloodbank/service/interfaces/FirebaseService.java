package group8.bloodbank.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FirebaseService {
    String upload(MultipartFile multipartFile) throws IOException;
}
