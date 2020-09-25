package com.example.demo.requests;

import lombok.Getter;

@Getter
public class ProductRequest {
    private Long id;
    private String name;
    private String creationDate;
    private float price;
    private int stock;
    private boolean productStatus;
}
