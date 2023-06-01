package group8.bloodbank.rabbitmq.messages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MonthlySubscriptionResponseDTO {
    public int hospitalSubscriptionId;
    public SubscriptionResponseStatus status;
}
