package se.iths.axel.orderservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import se.iths.axel.orderservice.dto.ProductInfo;
import se.iths.axel.orderservice.dto.ProductStockRequest;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestClient restClient;

    public List<ProductInfo> decreaseStock(List<ProductStockRequest> items, String bearerToken) {
        try {
            return restClient.post()
                    .uri("/products/stock/decrease")
                    .header("Authorization", bearerToken)
                    .body(items)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
        } catch (RestClientResponseException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getResponseBodyAsString());
        }
    }
}
