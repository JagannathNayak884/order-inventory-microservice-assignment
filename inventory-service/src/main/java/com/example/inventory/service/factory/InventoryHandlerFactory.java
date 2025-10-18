package com.example.inventory.service.factory;

import org.springframework.stereotype.Component;

@Component
public class InventoryHandlerFactory {

    private final PerishableInventoryHandler perishableHandler;

    public InventoryHandlerFactory(PerishableInventoryHandler perishableHandler) {
        this.perishableHandler = perishableHandler;
    }

    // For now, return perishable handler; can extend based on product type or metadata
    public InventoryHandler getHandler(String type) {
        return perishableHandler;
    }
}
