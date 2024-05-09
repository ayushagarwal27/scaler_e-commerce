package org.example.scaler_e_commerce.controllers;

import org.example.scaler_e_commerce.dtos.CategoryDto;
import org.example.scaler_e_commerce.exceptions.NotFoundException;
import org.example.scaler_e_commerce.models.Category;
import org.example.scaler_e_commerce.models.Product;
import org.example.scaler_e_commerce.services.SelfCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    private final SelfCategoryService selfCategoryService;

    CategoryController(SelfCategoryService selfCategoryService) {
        this.selfCategoryService = selfCategoryService;
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        Optional<List<Category>> categoryListOptional = selfCategoryService.getAllCategories();
        return categoryListOptional.get();
    }

    @GetMapping("/{categoryName}")
    public List<Product> getAllProductsByCategory(@PathVariable("categoryName") String categoryName) throws NotFoundException {
        Optional<List<Product>> productListOptional = selfCategoryService.getAllProductsByCategory(categoryName);
        if (productListOptional.isEmpty()) {
            throw new NotFoundException("Product with given category not found");
        }
        return productListOptional.get();
    }

    @PostMapping()
    public Category addNewCategory(@RequestBody CategoryDto categoryDto) {
        Optional<Category> optionalCategory = selfCategoryService.addNewCategory(categoryDto);
        return optionalCategory.get();
    }

}
