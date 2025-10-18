package com.example.inventory.repository;

import com.example.inventory.entity.ProductBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<ProductBatch, Long> {
    List<ProductBatch> findByProductIdOrderByExpiryDateAsc(Long productId);
}
