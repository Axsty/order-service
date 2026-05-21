package se.iths.axel.orderservice.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPublisher {

    private final RabbitTemplate template;

    // TODO: Skicka meddelande till mail-service.
}
