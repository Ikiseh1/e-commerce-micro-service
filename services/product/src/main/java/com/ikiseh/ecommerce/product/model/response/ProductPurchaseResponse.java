package com.ikiseh.ecommerce.product.model.response;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductPurchaseResponse {

    Integer productId;

    String name;

    String enterprise;

    BigDecimal price;

    double quantity;
}
