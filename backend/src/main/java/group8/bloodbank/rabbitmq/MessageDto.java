package group8.bloodbank.rabbitmq;

import java.util.Date;

public class MessageDto {
    private String text;

    public MessageDto() {
    }

    public MessageDto(String text) {
        this.text = text;

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
