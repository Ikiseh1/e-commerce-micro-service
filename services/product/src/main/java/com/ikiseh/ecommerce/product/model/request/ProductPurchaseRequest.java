package com.ikiseh.ecommerce.product.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductPurchaseRequest {


    @NotNull(message = "Product is mandatory")
    Integer productId;

    @NotNull(message = "Quantity is mandatory")
    double quantity;


}
