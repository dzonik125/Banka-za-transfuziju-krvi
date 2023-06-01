package group8.bloodbank.rabbitmq.messages;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class MonthlySubscriptionDeliveryDTO {
    public int hospitalSubscriptionId;
    public boolean delivered;
}
