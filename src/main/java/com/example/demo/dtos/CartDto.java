package com.example.demo.dtos;

import lombok.*;

import java.util.List;

@Builder
@Getter
public class CartDto {
    private List<CartItemDto> cart;
    private float total;
    private String date;
}
