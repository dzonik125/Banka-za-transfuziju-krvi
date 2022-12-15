package group8.bloodbank.rabbitmq.messages;


import org.springframework.web.multipart.MultipartFile;

public class MessageDto {

    private String text;
    private String subject;
    private String imgSrc;

    private String bloodBankApiKey;

    public MessageDto() {
    }

    public MessageDto(String text, String subject, String image, String bloodBankApiKey) {
        this.text = text;
        this.subject = subject;
        this.imgSrc = image;
        this.bloodBankApiKey = bloodBankApiKey;
    }


    public String getBloodBankApiKey() {
        return bloodBankApiKey;
    }

    public void setBloodBankApiKey(String bloodBankApiKey) {
        this.bloodBankApiKey = bloodBankApiKey;
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

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "text='" + text + '\'' +
                '}';
    }
}
