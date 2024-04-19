package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.example.scaler_e_commerce.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<List<Product>> getAllProducts();

    Optional<Product> getSingleProduct(Long productID);

    Product addNewProduct(ProductDto productDto);

    Optional<Product> updateSingleProduct(Long productID, ProductDto productDto);

    Optional<Product> replaceSingleProduct(Long productID, ProductDto productDto);

    Optional<Product> deleteSingleProduct(Long productID);
}
