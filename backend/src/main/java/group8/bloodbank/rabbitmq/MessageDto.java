package group8.bloodbank.rabbitmq;


import org.springframework.web.multipart.MultipartFile;

public class MessageDto {

    private String text;
    private String subject;
    private String image;
    private byte[] byteArray;

    public MessageDto() {
    }

    public MessageDto(String text, String subject, String image) {
        this.text = text;
        this.subject = subject;
        this.image = image;
        this.byteArray = java.util.Base64.getDecoder().decode(this.image);
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "text='" + text + '\'' +
                '}';
    }
}
