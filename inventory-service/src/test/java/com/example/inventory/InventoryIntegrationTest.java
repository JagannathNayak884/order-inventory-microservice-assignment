package com.example.inventory;

import com.example.inventory.entity.ProductBatch;
import com.example.inventory.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InventoryIntegrationTest {

    @Autowired
    InventoryRepository repo;

    @Test
    void contextLoadsAndRepoWorks() {
        repo.save(new ProductBatch(100L, 5, LocalDate.now().plusDays(5)));
        List<ProductBatch> list = repo.findByProductIdOrderByExpiryDateAsc(100L);
        assertEquals(1, list.size());
    }
}
