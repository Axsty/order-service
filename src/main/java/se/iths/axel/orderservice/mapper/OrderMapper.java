package se.iths.axel.orderservice.mapper;

import org.mapstruct.Mapper;
import se.iths.axel.orderservice.dto.CreateOrderRequest;
import se.iths.axel.orderservice.dto.OrderResponse;
import se.iths.axel.orderservice.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(CreateOrderRequest request);

    OrderResponse toOrderResponse(Order order);
}
