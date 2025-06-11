package com.ikiseh.ecommerce.product.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {

    Integer id;

    @NotNull(message = "Product name is required")
    String name;

    @NotNull(message = "Product description is required")
    String description;

    @Positive(message = "Available quantity should be positive")
    double availableQuantity;

    @Positive(message = "Price should be positive")
    BigDecimal price;

    @NotNull(message = "Product Category is required")
    Integer categoryId;



}
