package com.shopping.shoppingapp.product.service;

import com.shopping.shoppingapp.auth.entities.User;
import com.shopping.shoppingapp.auth.repository.UserRepository;
import com.shopping.shoppingapp.product.dto.*;
import com.shopping.shoppingapp.product.entities.*;
import com.shopping.shoppingapp.product.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    public ProductDto createProduct(ProductRequest request) {
        Category category = categoryRepository.findByName(request.getCategoryName())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Product product = Product.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .price(request.getPrice())
                .category(category)
                .build();

        product = productRepository.save(product);
        return toDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return toDto(product);
    }

    @Override
    public List<ProductDto> getProductsByCategory(String categoryName) {
        return productRepository.findByCategory_Name(categoryName)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto addReview(Long productId, String userEmail, ReviewRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Review review = Review.builder()
                .comment(request.getComment())
                .rating(request.getRating())
                .product(product)
                .user(user)
                .build();

        review = reviewRepository.save(review);

        return ReviewDto.builder()
                .id(review.getId())
                .comment(review.getComment())
                .rating(review.getRating())
                .userName(user.getName())
                .build();
    }

    private ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .price(product.getPrice())
                .categoryName(product.getCategory().getName())
                .reviews(
                        product.getReviews() == null ? List.of() :
                                product.getReviews().stream()
                                        .map(r -> ReviewDto.builder()
                                                .id(r.getId())
                                                .comment(r.getComment())
                                                .rating(r.getRating())
                                                .userName(r.getUser().getName())
                                                .build())
                                        .collect(Collectors.toList())
                )
                .build();
    }
}
