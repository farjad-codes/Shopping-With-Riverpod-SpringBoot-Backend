package com.shopping.shoppingapp.common.response;

// ApiResponse.java
public record ApiResponse<T>(
        String message,
        T data
) {}
