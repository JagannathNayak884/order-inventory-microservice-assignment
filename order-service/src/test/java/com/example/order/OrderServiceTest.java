package com.example.order;

import com.example.order.dto.OrderRequest;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Test
    void testPlaceOrder_whenInventoryUnavailable_returnsMessage() {
        OrderRepository repo = mock(OrderRepository.class);
        RestTemplate rt = mock(RestTemplate.class);
        when(rt.getForObject(anyString(), eq(Object[].class))).thenReturn(new Object[0]);

        OrderService svc = new OrderService(repo, rt);
        OrderRequest req = new OrderRequest();
        req.productId = 1L;
        req.quantity = 2;
        String res = svc.placeOrder(req);
        assertEquals("Product not available", res);
    }
}
