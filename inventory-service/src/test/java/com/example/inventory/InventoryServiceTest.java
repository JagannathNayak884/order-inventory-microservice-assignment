package com.example.inventory;

import com.example.inventory.entity.ProductBatch;
import com.example.inventory.repository.InventoryRepository;
import com.example.inventory.service.InventoryService;
import com.example.inventory.service.factory.InventoryHandlerFactory;
import com.example.inventory.service.factory.PerishableInventoryHandler;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceTest {

    @Test
    void testGetBatchesDelegatesToFactoryHandler() {
        InventoryRepository repo = mock(InventoryRepository.class);
        PerishableInventoryHandler handler = spy(new PerishableInventoryHandler(repo));
        InventoryHandlerFactory factory = mock(InventoryHandlerFactory.class);
        when(factory.getHandler(anyString())).thenReturn(handler);

        InventoryService svc = new InventoryService(repo, factory);
        when(repo.findByProductIdOrderByExpiryDateAsc(1L)).thenReturn(List.of(
                new ProductBatch(1L, 10, LocalDate.now().plusDays(1))
        ));

        var batches = svc.getBatches(1L);
        assertEquals(1, batches.size());
        verify(factory).getHandler("PERISHABLE");
    }
}
