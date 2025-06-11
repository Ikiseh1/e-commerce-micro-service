package com.ikiseh.ecommerce.product.model.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {

    Integer id;

    String name;

    String description;

    double availableQuantity;

    BigDecimal price;

    Integer categoryId;

    String categoryName;

    String categoryDescription;
}
