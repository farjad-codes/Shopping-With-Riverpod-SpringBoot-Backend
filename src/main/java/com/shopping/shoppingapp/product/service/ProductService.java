package com.shopping.shoppingapp.product.service;

import com.shopping.shoppingapp.product.dto.*;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductRequest request);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    List<ProductDto> getProductsByCategory(String categoryName);
    ReviewDto addReview(Long productId, String userEmail, ReviewRequest request);
}
