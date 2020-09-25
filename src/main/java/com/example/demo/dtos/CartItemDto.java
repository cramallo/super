package com.example.demo.dtos;

import com.example.demo.models.Product;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartItemDto {
    private Product product;
    private int count;
    private float subtotal;
}
