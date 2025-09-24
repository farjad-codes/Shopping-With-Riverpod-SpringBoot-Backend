package com.shopping.shoppingapp.product.repository;

import com.shopping.shoppingapp.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_Name(String categoryName);
}
