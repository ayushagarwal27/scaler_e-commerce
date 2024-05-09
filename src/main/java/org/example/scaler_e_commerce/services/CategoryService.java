package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.dtos.CategoryDto;
import org.example.scaler_e_commerce.models.Category;
import org.example.scaler_e_commerce.models.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<List<Category>> getAllCategories();

    Optional<List<Product>> getAllProductsByCategory(String categoryName);

    Optional<Category> addNewCategory(CategoryDto categoryDto);
}
