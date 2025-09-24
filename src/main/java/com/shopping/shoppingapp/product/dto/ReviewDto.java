package com.shopping.shoppingapp.product.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewDto {
    private Long id;
    private String comment;
    private int rating;
    private String userName;
}
