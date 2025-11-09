package co.kr.muldum.adapter.out.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    // Exchange 이름
    public static final String TEAM_EXCHANGE = "team.exchange";

    // Queue 이름
    public static final String TEAM_QUEUE = "team.queue";
    public static final String TEAM_RESPONSE_QUEUE = "team.response.queue";

    // Routing Key
    public static final String TEAM_ROUTING_KEY = "team.get";
    public static final String TEAM_RESPONSE_ROUTING_KEY = "team.response";

    @Bean
    public DirectExchange teamExchange() {
        return new DirectExchange(TEAM_EXCHANGE);
    }

    @Bean
    public Queue teamQueue() {
        return new Queue(TEAM_QUEUE, true);
    }

    @Bean
    public Queue teamResponseQueue() {
        return new Queue(TEAM_RESPONSE_QUEUE, true);
    }

    @Bean
    public Binding teamBinding(Queue teamQueue, DirectExchange teamExchange) {
        return BindingBuilder.bind(teamQueue).to(teamExchange).with(TEAM_ROUTING_KEY);
    }

    @Bean
    public Binding teamResponseBinding(Queue teamResponseQueue, DirectExchange teamExchange) {
        return BindingBuilder.bind(teamResponseQueue).to(teamExchange).with(TEAM_RESPONSE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }
}
