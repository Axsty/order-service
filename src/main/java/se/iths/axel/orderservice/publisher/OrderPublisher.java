package se.iths.axel.orderservice.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import se.iths.axel.orderservice.config.RabbitConfig;
import se.iths.axel.orderservice.dto.OrderResponse;

@Component
@RequiredArgsConstructor
public class OrderPublisher {

    private final RabbitTemplate template;

    public void sendOrderConfirmation(OrderResponse response) {
        template.convertAndSend(RabbitConfig.QUEUE, response);
        System.out.println("Order confirmation sent: " + response.id());
    }
}
