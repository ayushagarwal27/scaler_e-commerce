package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.dtos.ProductDto;

public interface ProductService {
    ProductDto[] getAllProducts();

    ProductDto getSingleProduct(Long productID);

    ProductDto addSingleProduct(ProductDto productDto);

    String updateSingleProduct(Long productID, ProductDto productDto);

    String deleteSingleProduct(Long productID);
}
