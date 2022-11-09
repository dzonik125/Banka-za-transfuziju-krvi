package group8.bloodbank.rabbitmq;


import org.springframework.web.multipart.MultipartFile;

public class MessageDto {

    private String text;
    private String subject;
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public MessageDto() {
    }

    public MessageDto(String text, String subject) {
        this.text = text;
        this.subject = subject;
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


    @Override
    public String toString() {
        return "MessageDto{" +
                "text='" + text + '\'' +
                '}';
    }
}
