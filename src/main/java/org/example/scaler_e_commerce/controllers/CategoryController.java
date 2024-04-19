package org.example.scaler_e_commerce.controllers;

import org.example.scaler_e_commerce.exceptions.NotFoundException;
import org.example.scaler_e_commerce.models.Category;
import org.example.scaler_e_commerce.models.Product;
import org.example.scaler_e_commerce.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {
    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        Optional<List<Category>> categoryListOptional = categoryService.getAllCategories();
        if (categoryListOptional.isEmpty()) {
            return new ArrayList<Category>();
        }
        return categoryListOptional.get();
    }

    @GetMapping("/{categoryName}")
    public List<Product> getAllProductsByCategory(@PathVariable("categoryName") String categoryName) throws NotFoundException {
        Optional<List<Product>> productListOptional = categoryService.getAllProductsByCategory(categoryName);
        if (productListOptional.isEmpty()) {
            throw new NotFoundException("Product with given category not found");
        }
        return productListOptional.get();
    }

}
