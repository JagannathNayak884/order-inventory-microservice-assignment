package com.example.order.dto;

public class OrderResponse {
    public boolean success;
    public String message;

    public OrderResponse() {}

    public OrderResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
