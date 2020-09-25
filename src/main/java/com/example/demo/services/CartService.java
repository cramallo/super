package com.example.demo.services;

import com.example.demo.dtos.CartDto;
import com.example.demo.dtos.CartItemDto;
import com.example.demo.models.CartItem;
import com.example.demo.models.Cart;
import com.example.demo.models.Product;
import com.example.demo.repositories.CartItemRepository;
import com.example.demo.repositories.CartRepository;
import com.example.demo.requests.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {
    final CartRepository cartRepository;
    final ProductService productService;
    final CartItemRepository cartItemRepository;

    @Autowired
    public CartService(final CartRepository cartRepository, final ProductService productService, final CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart saveProduct(final CartRequest cartRequest) {
        Cart cart = Cart.builder()
                .total(cartRequest.getTotal())
                .date(LocalDate.now().toString())
                .build();

        cartRepository.save(cart);

        List<CartItem> cartItems = cartRequest.getCart().stream().map(cartItem -> {
                    try {
                        return CartItem
                                .builder()
                                .count(cartItem.getCount())
                                .product(productService.getProduct(cartItem.getProduct()))
                                .subtotal(cartItem.getSubtotal())
                                .cart(cart)
                                .build();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        ).collect(Collectors.toList());

        this.cartItemRepository.saveAll(cartItems);

        cartRequest.getCart().stream().map(cartItem -> {
            try {
                Product product = productService.getProduct(cartItem.getProduct());
                int stock = product.getStock() - cartItem.getCount();
                product.setStock(Math.max(stock, 0));
                if (stock <= 0) {
                    product.setStatus(false);
                }
                productService.updateProduct(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });

        //cart.setCartItems(cartItems);

        //return this.cartRepository.save(cart);
        return cart;
    }

    public CartDto getSale(final long id) throws Exception {
        try {
            List<CartItem> cartItems = this.cartItemRepository.findByCartId(id);
            Optional<Cart> cart = this.cartRepository.findById(id);

            List<CartItemDto> cartItemDtos = cartItems.stream().map(item -> CartItemDto.builder()
                    .count(item.getCount())
                    .product(item.getProduct())
                    .subtotal(item.getSubtotal())
                    .build()).collect(Collectors.toList());

            CartDto cartDto = CartDto.builder()
                    .cart(cartItemDtos)
                    .total(cart.get().getTotal())
                    .date(cart.get().getDate())
                    .build();

            return cartDto;
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }

    public Page<Cart> getSales(final Pageable pageable) throws Exception {
        try {
            return this.cartRepository.findAll(pageable);
            //return buildProductPageDto(product);
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }
}
