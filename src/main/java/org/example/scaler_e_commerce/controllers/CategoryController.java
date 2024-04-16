package org.example.scaler_e_commerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    @GetMapping()
    public String getAllCategories() {
        return "Getting all categories";
    }

    @GetMapping("/{categoryID}")
    public String getAllProductsByCategory(@PathVariable("categoryID") Long categoryID) {
        return "Getting products with given category id: " + categoryID;
    }

}
