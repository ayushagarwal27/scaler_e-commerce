package org.example.scaler_e_commerce.controllers;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.example.scaler_e_commerce.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public ProductDto[] getAllProducts() {
        ProductDto[] products = productService.getAllProducts();
        return products;
    }

    @GetMapping("/{productID}")
    public ProductDto getSingleProduct(@PathVariable("productID") Long productID) {
        ProductDto product = productService.getSingleProduct(productID);
        return product;
    }

    @PostMapping()
    public ProductDto addSingleProduct(@RequestBody ProductDto productDto) {
        ProductDto product = productService.addSingleProduct(productDto);
        return product;
    }


    @PutMapping("/{productID}")
    public String updateSingleProduct(@PathVariable("productID") Long productID, @RequestBody ProductDto productDto) {
        return productService.updateSingleProduct(productID, productDto);
    }

    @DeleteMapping("/{productID}")
    public String deleteSingleProduct(@PathVariable("productID") Long productID) {
        return productService.deleteSingleProduct(productID);
    }
}
