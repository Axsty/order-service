package se.iths.axel.orderservice.dto;

public record ProductStockRequest(
        Long productId,
        int quantity
) {
}
