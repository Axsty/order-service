package se.iths.axel.orderservice.dto;

import java.util.List;

public record CreateOrderRequest(
        List<CreateOrderItemRequest> orderItems
) {
}
