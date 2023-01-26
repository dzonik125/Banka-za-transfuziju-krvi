package group8.bloodbank.service.implementations;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import group8.bloodbank.service.interfaces.FirebaseService;
import group8.bloodbank.service.interfaces.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    @Autowired
    FirebaseService firebaseService;

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode";

    @Override
    public String generateImageAsQRCode(String link, int width, int height) {
        String url = "";
        try {
            String filePath = generateQRCodeImage(link, width, height);
            File file = new File(filePath);
            String uniqueID = UUID.randomUUID().toString();
            url = firebaseService.upload(file, uniqueID);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return url;
    }

    public String generateQRCodeImage(String text, int width, int height)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        String uniqueID = UUID.randomUUID().toString();
        Path path = FileSystems.getDefault().getPath(QR_CODE_IMAGE_PATH + uniqueID + ".png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        return QR_CODE_IMAGE_PATH + uniqueID + ".png";
    }


    public byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig( 0xFF000002 , 0xFFFFC041 ) ;

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream,con);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }

}
