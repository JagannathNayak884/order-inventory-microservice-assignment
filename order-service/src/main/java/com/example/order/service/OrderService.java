package com.example.order.service;

import com.example.order.dto.OrderRequest;
import com.example.order.entity.OrderEntity;
import com.example.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public String placeOrder(OrderRequest req) {
        // Check availability by calling InventoryService GET /inventory/{productId}
        try {
            var batches = restTemplate.getForObject("http://localhost:8081/inventory/" + req.productId, Object[].class);
            if (batches == null || batches.length == 0) {
                return "Product not available";
            }
            // Try to update inventory by POST /inventory/update
            var update = new java.util.HashMap<String, Object>();
            update.put("productId", req.productId);
            update.put("quantity", req.quantity);
            var resp = restTemplate.postForObject("http://localhost:8081/inventory/update", update, String.class);
            if (resp != null && resp.contains("Inventory updated")) {
                // save order
                repository.save(new OrderEntity(req.productId, req.quantity));
                return "Order placed";
            } else {
                return "Insufficient stock";
            }
        } catch (RestClientException ex) {
            return "Inventory service error: " + ex.getMessage();
        }
    }
}
