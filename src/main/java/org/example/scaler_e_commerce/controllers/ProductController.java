package org.example.scaler_e_commerce.controllers;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {


    @GetMapping()
    public String getAllProducts() {
        return "All products got!";
    }

    @GetMapping("/{productID}")
    public String getSingleProduct(@PathVariable("productID") Long productID) {
        return "Getting details of product with ID: " + productID;
    }

    @PostMapping()
    public String addSingleProduct(@RequestBody ProductDto productDto) {
        return "Adding product" + productDto;
    }


    @PutMapping("/{productID}")
    public String updateSingleProduct(@PathVariable("productID") Long productID, @RequestBody ProductDto productDto) {
        return "Updating product with ID: " + productID + " " + productDto;
    }

    @DeleteMapping("/{productID}")
    public String deleteSingleProduct(@PathVariable("productID") Long productID) {
        return "Deleting product with given ID: " + productID;
    }
}
