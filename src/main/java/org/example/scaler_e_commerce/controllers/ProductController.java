package org.example.scaler_e_commerce.controllers;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.example.scaler_e_commerce.models.Product;
import org.example.scaler_e_commerce.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public List<Product> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return products;
    }

    @GetMapping("/{productID}")
    public Product getSingleProduct(@PathVariable("productID") Long productID) {
        return productService.getSingleProduct(productID);
    }

    @PostMapping()
    public Product addSingleProduct(@RequestBody ProductDto productDto) {
        Product product = productService.addNewProduct(productDto);
        return product;
    }


    @PatchMapping("/{productID}")
    public Product updateSingleProduct(@PathVariable("productID") Long productID, @RequestBody ProductDto productDto) {
        return productService.updateSingleProduct(productID, productDto);
    }

    @PutMapping("/{productID}")
    public Product replaceSingleProduct(@PathVariable("productID") Long productID, @RequestBody ProductDto productDto) {
        return productService.replaceSingleProduct(productID, productDto);
    }

    @DeleteMapping("/{productID}")
    public Product deleteSingleProduct(@PathVariable("productID") Long productID) {
        return productService.deleteSingleProduct(productID);
    }
}
