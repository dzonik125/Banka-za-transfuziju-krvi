package group8.bloodbank.rabbitmq;

import java.util.Date;

public class MessageDto {
    private String text;
    private String subject;



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
