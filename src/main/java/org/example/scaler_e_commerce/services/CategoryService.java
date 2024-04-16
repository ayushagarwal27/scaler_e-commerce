package org.example.scaler_e_commerce.services;

public interface CategoryService {
    String getAllCategories();

    String getAllProductsByCategory(Long categoryID);
}
