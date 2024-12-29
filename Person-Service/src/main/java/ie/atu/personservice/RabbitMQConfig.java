package ie.atu.personservice;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //queues constants
    public static final String PERSON_QUEUE = "personQueue";

    public static final String EXCHANGE = "personExchange";
    public static final String ROUTING_KEY = "person_routingKey";

    //declare queues
    @Bean
    public Queue personQueue(){
       return new Queue(PERSON_QUEUE, true);
    }
    //exchange here
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    //binding for binding keys ie this case anything starting with order for ordersQueue
    @Bean
    public Binding personQueueBinding(Queue personQueue, TopicExchange exchange){
        return BindingBuilder.bind(personQueue).to(exchange).with(ROUTING_KEY);
   }
   //message converter
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

   @Bean
   public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
   }
}

