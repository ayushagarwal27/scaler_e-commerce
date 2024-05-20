package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.dtos.CategoryDto;
import org.example.scaler_e_commerce.models.Category;
import org.example.scaler_e_commerce.models.Product;
import org.example.scaler_e_commerce.repositories.CategoryRepository;
import org.example.scaler_e_commerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelfCategoryService implements CategoryService {
    CategoryRepository categoryRepository;
    ProductRepository productRepository;

    SelfCategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Optional<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return Optional.of(categoryList);
    }
 
    @Override
    public Optional<List<Product>> getAllProductsByCategory(String categoryName) {
        List<Product> productList = productRepository.getProductsByCategoryName(categoryName);
        return Optional.of(productList);
    }

    private Category convertCategoryDtoToCategoryModel(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

    @Override
    public Optional<Category> addNewCategory(CategoryDto categoryDto) {
        Category category = convertCategoryDtoToCategoryModel(categoryDto);
        category = categoryRepository.save(category);
        return Optional.of(category);
    }
}
