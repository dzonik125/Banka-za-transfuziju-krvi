/*package group8.bloodbank.rabbitmq;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${custom.rabbitmq.queue}")
    private String queueName;

    @Value("${custom.rabbitmq.bloodRequestQueue}")
    private String bloodRequestQueue;

    @Value("${custom.rabbitmq.monthlySubscriptionsQueue}")
    private String monthlySubscriptionsQueue;

    @Value("${custom.rabbitmq.exchange}")
    private String exchange;

    @Value("${custom.rabbitmq.bloodRequestExchange}")
    private String bloodRequestExchange;

    @Value("${custom.rabbitmq.monthlySubscriptionsExchange}")
    private String monthlySubscriptionsExchange;
    @Value("${custom.rabbitmq.routingKey}")
    private String routingKey;

    @Value("${custom.rabbitmq.bloodRequestRoutingKey}")
    private String bloodRequestRoutingKey;

    @Value("${custom.rabbitmq.monthlySubscriptionsRoutingKey}")
    private String monthlySubscriptionsRoutingKey;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    Queue bloodRequestQueue() {
        return new Queue(bloodRequestQueue, false);
    }

    @Bean
    Queue monthlySubscriptionsQueue() {return  new Queue(monthlySubscriptionsQueue, false);}

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    DirectExchange directSubscriptionsExchange() {return  new DirectExchange(monthlySubscriptionsExchange);}
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(bloodRequestExchange);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    Binding bloodRequestBinding(@Qualifier("bloodRequestQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(bloodRequestRoutingKey);
    }

    @Bean
    Binding monthlySubscriptionsBinding(@Qualifier("monthlySubscriptionsQueue") Queue queue, @Qualifier("directSubscriptionsExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(monthlySubscriptionsRoutingKey);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory ) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueues(monthlySubscriptionsQueue());
        simpleMessageListenerContainer.setMessageListener(inputMonthlySubscriptionsListener());
        return simpleMessageListenerContainer;

    }

    @Bean
    public MessageListenerContainer messageListenerContainer2(ConnectionFactory connectionFactory ) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueues(bloodRequestQueue());
        simpleMessageListenerContainer.setMessageListener(inputBloodUnitRequestListener());
        return simpleMessageListenerContainer;

    }

    @Bean
    public RabbitMQBloodUnitRequestListener inputBloodUnitRequestListener() {
        return new RabbitMQBloodUnitRequestListener();
    }

    @Bean
    public RabbitMQMonthlySubscriptionsListener inputMonthlySubscriptionsListener() {
        return new RabbitMQMonthlySubscriptionsListener();
    }


}*/
