package com.example.inventory.service.factory;

import com.example.inventory.entity.ProductBatch;
import java.util.List;

public interface InventoryHandler {
    // handle decrement of batches; returns true if successful
    boolean handleDecrease(Long productId, int quantity);
    List<ProductBatch> getBatches(Long productId);
}
