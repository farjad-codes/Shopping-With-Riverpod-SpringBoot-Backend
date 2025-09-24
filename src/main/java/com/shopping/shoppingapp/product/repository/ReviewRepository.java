package com.shopping.shoppingapp.product.repository;


import com.shopping.shoppingapp.product.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
