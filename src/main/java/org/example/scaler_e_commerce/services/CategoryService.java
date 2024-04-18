package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.models.Category;
import org.example.scaler_e_commerce.models.Product;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    List<Product> getAllProductsByCategory(String categoryName);
}
