package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.dtos.ProductDto;

public interface CategoryService {
    String[] getAllCategories();

    ProductDto[] getAllProductsByCategory(String categoryName);
}
