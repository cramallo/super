package com.example.demo.requests;

import lombok.Getter;

@Getter
public class CartItemRequest {
    private long product;
    private int count;
    private float subtotal;
}
