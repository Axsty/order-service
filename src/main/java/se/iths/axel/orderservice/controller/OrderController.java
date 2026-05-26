package se.iths.axel.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.axel.orderservice.dto.CreateOrderRequest;
import se.iths.axel.orderservice.dto.OrderResponse;
import se.iths.axel.orderservice.publisher.OrderPublisher;
import se.iths.axel.orderservice.service.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderPublisher publisher;
    private final OrderService service;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest request, @AuthenticationPrincipal Jwt jwt) {
        String bearerToken = "Bearer " + jwt.getTokenValue();

        OrderResponse response = service.createOrder(request, jwt.getSubject(), bearerToken);
        publisher.sendOrderConfirmation(response);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
