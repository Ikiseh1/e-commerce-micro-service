package com.ikiseh.ecommerce.product.service;

import com.ikiseh.ecommerce.product.exception.ProductPurchaseException;
import com.ikiseh.ecommerce.product.model.request.ProductPurchaseRequest;
import com.ikiseh.ecommerce.product.model.request.ProductRequest;
import com.ikiseh.ecommerce.product.model.response.ProductPurchaseResponse;
import com.ikiseh.ecommerce.product.model.response.ProductResponse;
import com.ikiseh.ecommerce.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public Integer createProduct(ProductRequest productRequest) {

        var product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> productPurchaseRequests) {
        //extract product id's
        var productIds = productPurchaseRequests
                .stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();

        //check if the id's exists in the database'
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        //if the size of the 2 list are different throw error
        if (productIds.size() != storedProducts.size()){
            throw new ProductPurchaseException("One or more products does not exists");
        }

        //extract id of our storedRequest
        var storesRequest = productPurchaseRequests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();

        //create an object of purchased product
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        //check to ensure quantity to be purchased is available in the db
        for (int i = 0; i < storedProducts.size(); i++){
            var product = storedProducts.get(i);
            var productRequest = storesRequest.get(i);
            if (product.getAvailableQuantity() <productRequest.getQuantity()){
                throw new ProductPurchaseException("Insufficient Stock Quantity for product with ID::" + productRequest.getProductId());
            }

            //re-calculate the available quantity
            var newAvailableQuantity  = product.getAvailableQuantity() - productRequest.getQuantity();
            //updating the quantity
            product.setAvailableQuantity(newAvailableQuantity);
            //storing the new quantity in the db
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.getQuantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the ID:: " + productId));
    }

    public List<ProductResponse> findAll(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
