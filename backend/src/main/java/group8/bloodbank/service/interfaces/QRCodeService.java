package group8.bloodbank.service.interfaces;

public interface QRCodeService {

    String generateImageAsQRCode(String text, int widht, int heght, String path);
}
