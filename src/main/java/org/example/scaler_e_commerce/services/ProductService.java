package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.dtos.ProductDto;

public interface ProductService {
    String getAllProducts();

    String getSingleProduct(Long productID);

    String addSingleProduct(ProductDto productDto);

    String updateSingleProduct(Long productID, ProductDto productDto);

    String deleteSingleProduct(Long productID);
}
