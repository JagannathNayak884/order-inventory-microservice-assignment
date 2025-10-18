package com.example.order;

import com.example.order.entity.OrderEntity;
import com.example.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderIntegrationTest {

    @Autowired
    OrderRepository repo;

    @Test
    void repoSaves() {
        var o = new OrderEntity(5L, 3);
        repo.save(o);
        assertTrue(repo.findAll().size() >= 1);
    }
}
