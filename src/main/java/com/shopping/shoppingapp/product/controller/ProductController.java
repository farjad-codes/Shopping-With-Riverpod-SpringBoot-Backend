package com.shopping.shoppingapp.product.controller;

import com.shopping.shoppingapp.product.dto.*;
import com.shopping.shoppingapp.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductDto createProduct(@Valid @RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/category/{categoryName}")
    public List<ProductDto> getProductsByCategory(@PathVariable String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }

    @PostMapping("/{id}/reviews")
    public ReviewDto addReview(@PathVariable Long id,
                               @Valid @RequestBody ReviewRequest request,
                               Principal principal) {
        return productService.addReview(id, principal.getName(), request);
    }
}
