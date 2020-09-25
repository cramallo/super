package com.example.demo.controllers;

import com.example.demo.dtos.ProductPageDto;
import com.example.demo.models.Product;
import com.example.demo.requests.ProductRequest;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product buildProduct(@RequestBody final ProductRequest productRequest) {
        return this.productService.saveProduct(productRequest);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") final Long id) throws Exception {
        return this.productService.getProduct(id);
    }

    @GetMapping
    public ProductPageDto getProducts(@RequestParam(value = "status", required = false) boolean status,
                                      final Pageable pageable) throws Exception {
        return this.productService.getProducts(status, pageable);
    }

    @PutMapping
    public Product getProducts(@RequestBody final Product product) throws Exception {
        return this.productService.updateProduct(product);
    }

}
