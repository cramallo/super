package com.example.demo.dtos;

import com.example.demo.models.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ProductPageDto {
    List<Product> products;
    private int actualPage;
    private int totalPages;
    private int numberOfElementsPerPage;
}
