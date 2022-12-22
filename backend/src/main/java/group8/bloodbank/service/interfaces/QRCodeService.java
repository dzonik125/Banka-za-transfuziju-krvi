package group8.bloodbank.service.interfaces;

public interface QRCodeService {

    String generateImageAsQRCode(String link, int width, int height, String appointmentId);

}
