package com.example.demo.services;

import com.example.demo.dtos.ProductPageDto;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.requests.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(final ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .status(true)
                .build();
        return this.productRepository.save(product);
    }

    public Product updateProduct(final Product product) {
        return this.productRepository.save(product);
    }

    public Product getProduct(final long id) throws Exception {
        try {
            Optional<Product> product = this.productRepository.findById(id);
            if (product.isPresent()) {
                return product.get();
            }
            return null;
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }

    public ProductPageDto getProducts(final boolean status, final Pageable pageable) throws Exception {
        try {
            final Page<Product> product = this.productRepository.findAllByParams(status, pageable);
            return buildProductPageDto(product);
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }

    public Product decreaseStock(Long idProduct, int decreaseStock) throws Exception {
        try {
            Product product = getProduct(idProduct);
            product.setStock(product.getStock() - decreaseStock);
            return productRepository.save(product);
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }

    private ProductPageDto buildProductPageDto(final Page<Product> product) {
        return ProductPageDto.builder()
                .actualPage(product.getNumber() + 1)
                .numberOfElementsPerPage(product.getSize())
                .totalPages(product.getTotalPages())
                .products(product.getContent())
                .build();
    }
}
