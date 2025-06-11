package com.ikiseh.ecommerce.product.service;

import com.ikiseh.ecommerce.product.entity.Category;
import com.ikiseh.ecommerce.product.entity.Product;
import com.ikiseh.ecommerce.product.model.request.ProductRequest;
import com.ikiseh.ecommerce.product.model.response.ProductPurchaseResponse;
import com.ikiseh.ecommerce.product.model.response.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest productRequest){
        return Product.builder()
                .id(productRequest.getId())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .availableQuantity(productRequest.getAvailableQuantity())
                .category(
                        Category.builder()
                                .id(productRequest.getCategoryId())
                                .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
