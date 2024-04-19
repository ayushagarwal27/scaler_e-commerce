package org.example.scaler_e_commerce.controllers;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.example.scaler_e_commerce.exceptions.NotFoundException;
import org.example.scaler_e_commerce.models.Product;
import org.example.scaler_e_commerce.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public List<Product> getAllProducts() throws NotFoundException {
        Optional<List<Product>> productListOptional = productService.getAllProducts();
        if (productListOptional.isEmpty()) {
            throw new NotFoundException("No products found.");
        }
        return productListOptional.get();
    }

    @GetMapping("/{productID}")
    public Product getSingleProduct(@PathVariable("productID") Long productID) throws NotFoundException {
        Optional<Product> productOptional = productService.getSingleProduct(productID);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found.");
        }
        return productOptional.get();
    }

    @PostMapping()
    public Product addSingleProduct(@RequestBody ProductDto productDto) {
        return productService.addNewProduct(productDto);
    }


    @PatchMapping("/{productID}")
    public Product updateSingleProduct(@PathVariable("productID") Long productID, @RequestBody ProductDto productDto) throws NotFoundException {
        Optional<Product> productOptional = productService.updateSingleProduct(productID, productDto);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found.");
        }
        return productOptional.get();
    }

    @PutMapping("/{productID}")
    public Product replaceSingleProduct(@PathVariable("productID") Long productID, @RequestBody ProductDto productDto) throws NotFoundException {
        Optional<Product> productOptional = productService.replaceSingleProduct(productID, productDto);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found.");
        }
        return productOptional.get();
    }

    @DeleteMapping("/{productID}")
    public Product deleteSingleProduct(@PathVariable("productID") Long productID) throws NotFoundException {
        Optional<Product> productOptional = productService.deleteSingleProduct(productID);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found.");
        }
        return productOptional.get();
    }
}
