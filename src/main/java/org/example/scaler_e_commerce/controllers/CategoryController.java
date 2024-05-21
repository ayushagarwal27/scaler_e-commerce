package org.example.scaler_e_commerce.controllers;

import org.example.scaler_e_commerce.dtos.CategoryDto;
import org.example.scaler_e_commerce.exceptions.NotFoundException;
import org.example.scaler_e_commerce.models.Category;
import org.example.scaler_e_commerce.models.Product;
import org.example.scaler_e_commerce.services.SelfCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Category>> getAllCategories() {
        Optional<List<Category>> categoryListOptional = selfCategoryService.getAllCategories();
        return ResponseEntity.ok(categoryListOptional.get());
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable("categoryName") String categoryName) throws NotFoundException {
        Optional<List<Product>> productListOptional = selfCategoryService.getAllProductsByCategory(categoryName);
        if (productListOptional.isEmpty()) {
            throw new NotFoundException("Product with given category not found");
        }
        return ResponseEntity.ok(productListOptional.get());
    }

    @PostMapping()
    public ResponseEntity<Category> addNewCategory(@RequestBody CategoryDto categoryDto) {
        Optional<Category> optionalCategory = selfCategoryService.addNewCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.OK).body(optionalCategory.get());
    }

}
