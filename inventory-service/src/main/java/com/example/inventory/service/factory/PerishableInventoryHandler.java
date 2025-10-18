package com.example.inventory.service.factory;

import com.example.inventory.entity.ProductBatch;
import com.example.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class PerishableInventoryHandler implements InventoryHandler {

    private final InventoryRepository repository;

    public PerishableInventoryHandler(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public boolean handleDecrease(Long productId, int quantity) {
        List<ProductBatch> batches = repository.findByProductIdOrderByExpiryDateAsc(productId);
        int remaining = quantity;
        for (ProductBatch b : batches) {
            if (remaining <= 0) break;
            int take = Math.min(b.getQuantity(), remaining);
            b.setQuantity(b.getQuantity() - take);
            remaining -= take;
            repository.save(b);
        }
        return remaining == 0;
    }

    @Override
    public List<ProductBatch> getBatches(Long productId) {
        return repository.findByProductIdOrderByExpiryDateAsc(productId);
    }
}
