package se.iths.axel.orderservice.dto;

public record CreateOrderItemRequest(
        Long productId,
        int quantity
) {
}
