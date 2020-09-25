package com.example.demo.requests;

import lombok.Getter;

import java.util.List;

@Getter
public class CartRequest {
    private List<CartItemRequest> cart;
    private float total;
}
