package se.iths.axel.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.axel.orderservice.client.ProductClient;
import se.iths.axel.orderservice.dto.CreateOrderRequest;
import se.iths.axel.orderservice.dto.OrderResponse;
import se.iths.axel.orderservice.dto.ProductInfo;
import se.iths.axel.orderservice.dto.ProductStockRequest;
import se.iths.axel.orderservice.mapper.OrderMapper;
import se.iths.axel.orderservice.model.Order;
import se.iths.axel.orderservice.model.OrderItem;
import se.iths.axel.orderservice.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final ProductClient client;

    /*CREATE ORDER*/
    public OrderResponse createOrder(CreateOrderRequest request, String username) {
        List<ProductStockRequest> stockRequest = request.orderItems()
                .stream()
                .map(item -> new ProductStockRequest(item.productId(), item.quantity()))
                .toList();

        List<ProductInfo> productInfo = client.decreaseStock(stockRequest, username);

        List<OrderItem> itemList = new ArrayList<>();

        for (ProductInfo info : productInfo) {

            OrderItem orderItem = new OrderItem();
            orderItem.setName(info.name());
            orderItem.setPrice(info.price());
            orderItem.setQuantity(info.quantity());
            itemList.add(orderItem);
        }

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setCustomerName(username);
        order.setOrderItems(itemList);
        order.setTotalPrice(totalPriceCalculation(itemList));

        repository.save(order);


        return mapper.toOrderResponse(order);
    }

    public BigDecimal totalPriceCalculation(List<OrderItem> itemList) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (OrderItem orderItem : itemList) {
            totalPrice = totalPrice.add(orderItem.getPrice()
                    .multiply(new BigDecimal(orderItem.getQuantity())));
        }

        return totalPrice;
    }
}
