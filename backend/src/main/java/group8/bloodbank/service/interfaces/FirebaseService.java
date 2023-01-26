package group8.bloodbank.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FirebaseService {
    String upload(File file, String appointmentId) throws IOException;
}
