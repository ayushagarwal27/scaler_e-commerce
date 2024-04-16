package org.example.scaler_e_commerce.controllers;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.example.scaler_e_commerce.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {
    private CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String[] getAllCategories() {
        categoryService.getAllCategories();
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryName}")
    public ProductDto[] getAllProductsByCategory(@PathVariable("categoryName") String categoryName) {
        return categoryService.getAllProductsByCategory(categoryName);
    }

}
