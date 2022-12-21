package group8.bloodbank.service.interfaces;

public interface QRCodeService {

    String generateImageAsQRCode(String link, int widht, int heght, String path, String appointmentId);
}
