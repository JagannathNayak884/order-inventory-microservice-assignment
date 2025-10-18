package com.example.inventory.controller;

import com.example.inventory.entity.ProductBatch;
import com.example.inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductBatch>> getBatches(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getBatches(productId));
    }

    public static class UpdateRequest {
        public Long productId;
        public int quantity;
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateInventory(@RequestBody UpdateRequest req) {
        boolean ok = service.decreaseStock(req.productId, req.quantity);
        if (ok) return ResponseEntity.ok("Inventory updated");
        return ResponseEntity.status(400).body("Insufficient stock");
    }
}
