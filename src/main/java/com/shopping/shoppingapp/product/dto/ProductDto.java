package com.shopping.shoppingapp.product.dto;


import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    private String categoryName;
    private List<ReviewDto> reviews;
}
