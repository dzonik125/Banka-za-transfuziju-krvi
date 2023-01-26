package group8.bloodbank.service.interfaces;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QRCodeService {

    String generateImageAsQRCode(String link, int width, int height);

    String generateQRCodeImage(String text, int width, int height) throws WriterException, IOException;

    public byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException;

}
