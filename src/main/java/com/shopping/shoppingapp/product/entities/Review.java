package com.shopping.shoppingapp.product.entities;

import com.shopping.shoppingapp.auth.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "reviews")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String comment;

    @Min(1) @Max(5)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;   // comes from auth module

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
