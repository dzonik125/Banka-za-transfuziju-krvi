package group8.bloodbank.service.implementations;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import group8.bloodbank.service.interfaces.FirebaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Service
public class FirebaseServiceImpl implements FirebaseService {

    String firebaseUrl = "https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/%s?alt=media";

    @Override
    public String upload(MultipartFile multipartFile, String appointmentId) throws IOException {
            String fileName = "qr_appointment" + appointmentId;
            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String url = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return url;
    }


    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("isapsw-6ef61.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/png").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/authcode/isapsw-6ef61-firebase-adminsdk-gnyv2-8e9e56fa97.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(firebaseUrl, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }
}
