package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.example.scaler_e_commerce.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(Long productID);

    Product addNewProduct(ProductDto productDto);

    Product updateSingleProduct(Long productID, ProductDto productDto);

    Product replaceSingleProduct(Long productID, ProductDto productDto);

    Product deleteSingleProduct(Long productID);
}
