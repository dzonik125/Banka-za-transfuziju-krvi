package group8.bloodbank.service.implementations;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import group8.bloodbank.service.interfaces.FirebaseService;
import group8.bloodbank.service.interfaces.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    @Autowired
    FirebaseService firebaseService;

    @Override
    public String generateImageAsQRCode(String link, int widht, int heght, String appointmentId) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String url = "";


        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(link, BarcodeFormat.QR_CODE, widht, heght);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", FileSystems.getDefault().getPath("src/main/resources/QRCode" + appointmentId +".png"));
            File is = null;
            while(is == null) {
                is = getFileFromResource("QRCode" + appointmentId +".png");
            }
            FileInputStream input = new FileInputStream(is);
            MultipartFile multipartFile = new MockMultipartFile("QRCode" + appointmentId + ".png",
                    is.getName(), "image/png", input.readAllBytes());
            url = firebaseService.upload(multipartFile, appointmentId);

        } catch (WriterException | IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return url;
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            return null;
            //throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

}
