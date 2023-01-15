package group8.bloodbank.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import group8.bloodbank.service.interfaces.MedicalWorkerService;
import group8.bloodbank.service.interfaces.QRCodeService;
import group8.bloodbank.service.interfaces.WorkingHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/newController")
public class NewController {

    private QRCodeService qrCodeService;

    @Autowired
    public NewController(QRCodeService qrCodeService){
        this.qrCodeService = qrCodeService;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void nesto() throws NotFoundException, IOException {
            String s =  qrCodeService.generateImageAsQRCode("", 200, 200, "1");
            // Path where the QR code is saved
            String path = "C:"+File.separator+"Users"+File.separator+"User"+ File.separator + "Desktop"+ File.separator +"qrcode2.jpg";

            // Encoding charset
            String charset = "UTF-8";

            Map<EncodeHintType, ErrorCorrectionLevel> hashMap
                    = new HashMap<EncodeHintType,
                    ErrorCorrectionLevel>();

            hashMap.put(EncodeHintType.ERROR_CORRECTION,
                    ErrorCorrectionLevel.L);

            System.out.println(
                    "QRCode output: "
                            + readQRCode(path, charset, hashMap));
    }

    public static String readQRCode(String filePath, String charset, Map hintMap)
            throws FileNotFoundException, IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(
                        ImageIO.read(new FileInputStream(filePath)))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
                hintMap);

        String s = qrCodeResult.getText();
        return qrCodeResult.getText();
    }

//    @Autowired
//    public MedicalWorkerController(MedicalWorkerService medicalWorkerService, WorkingHoursService workingHoursService){
//        this.medicalWorkerService = medicalWorkerService;
//        this.workingHoursService = workingHoursService;
//    }


}
