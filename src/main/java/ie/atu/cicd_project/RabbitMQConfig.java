package ie.atu.cicd_project;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    public static final String ORDERS_QUEUE = "ordersQueue";
    public static final String INVENTORY_UPDATE_QUEUE = "inventoryUpdateQueue";
    public static final String PAYMENT_PROCESSED_QUEUE = "paymentProcessedQueue";
    public static final String EXCHANGE = "orderExchange";
    @Bean
   public Queue ordersQueue(){
       return new Queue(ORDERS_QUEUE, true);
   }

   @Bean
    public Queue inventoryUpdateQueue(){
        return new Queue(INVENTORY_UPDATE_QUEUE, true);
    }

    @Bean
    public Queue paymentProcessedQueue(){
        return new Queue(PAYMENT_PROCESSED_QUEUE, true);
    }
    @Bean
    public Queue orderExchange(){
        return new Queue(EXCHANGE, true);
    }

    @Bean
    public Binding ordersQueueBinding(Queue ordersQueue, TopicExchange exchange){
        return BindingBuilder.bind(ordersQueue).to(exchange).with("order.#");
    }

}

