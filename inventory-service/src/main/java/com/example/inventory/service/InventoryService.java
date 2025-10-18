package com.example.inventory.service;

import com.example.inventory.entity.ProductBatch;
import com.example.inventory.repository.InventoryRepository;
import com.example.inventory.service.factory.InventoryHandlerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository repository;
    private final InventoryHandlerFactory factory;

    public InventoryService(InventoryRepository repository, InventoryHandlerFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public List<ProductBatch> getBatches(Long productId) {
        return factory.getHandler("PERISHABLE").getBatches(productId);
    }

    public boolean decreaseStock(Long productId, int quantity) {
        return factory.getHandler("PERISHABLE").handleDecrease(productId, quantity);
    }

    public ProductBatch saveBatch(ProductBatch batch) {
        return repository.save(batch);
    }
}
