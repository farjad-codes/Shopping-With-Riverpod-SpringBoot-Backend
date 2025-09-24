package com.shopping.shoppingapp.product.dto;

import java.util.List;

public record ProductResponse(
        Long id,
        String title,
        String description,
        String imageUrl,
        Double price,
        String category,
        List<ReviewDto> reviews
) { }
