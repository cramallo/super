package com.example.demo.controllers;

import com.example.demo.dtos.CartDto;
import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.requests.CartRequest;
import com.example.demo.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public Cart createSale(@RequestBody final CartRequest cartRequest) {
        return this.cartService.saveProduct(cartRequest);
    }

    @GetMapping("/{id}")
    public CartDto getSale(@PathVariable("id") final Long id) throws Exception {
        return this.cartService.getSale(id);
    }

    @GetMapping
    public Page<Cart> getSales(final Pageable pageable) throws Exception {
        return this.cartService.getSales(pageable);
    }

}
