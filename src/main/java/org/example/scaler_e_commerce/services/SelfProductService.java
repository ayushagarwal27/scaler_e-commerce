package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.models.Product;
import org.example.scaler_e_commerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SelfProductService {
    ProductRepository productRepository;

    SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getSingleProduct(Long productID) {
        Product product = productRepository.getProductById(productID);
        if (product == null) {
            return Optional.empty();
        }
        return Optional.of(product);
    }
}
