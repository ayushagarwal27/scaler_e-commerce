package org.example.scaler_e_commerce.controllers;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.example.scaler_e_commerce.exceptions.NotFoundException;
import org.example.scaler_e_commerce.models.Product;
import org.example.scaler_e_commerce.services.SelfProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final SelfProductService selfProductService;

    ProductController(SelfProductService selfProductService) {
        this.selfProductService = selfProductService;
    }


    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() throws NotFoundException {
        Optional<List<Product>> productListOptional = selfProductService.getAllProducts();
        if (productListOptional.get().isEmpty()) {
            throw new NotFoundException("No products found.");
        }
        return ResponseEntity.ok(productListOptional.get());
    }

    @GetMapping("/{productID}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productID") Long productID) throws NotFoundException {
        Optional<Product> productOptional = selfProductService.getSingleProduct(productID);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found.");
        }
        return ResponseEntity.ok(productOptional.get());
    }

    @PostMapping()
    public ResponseEntity<Product> addSingleProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(selfProductService.addNewProduct(productDto));
    }


    @PatchMapping("/{productID}")
    public ResponseEntity<Product> updateSingleProduct(@PathVariable("productID") Long productID, @RequestBody ProductDto productDto) throws NotFoundException {
        Optional<Product> productOptional = selfProductService.updateSingleProduct(productID, productDto);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found.");
        }
        return ResponseEntity.accepted().body(productOptional.get());
    }

    @PutMapping("/{productID}")
    public ResponseEntity<Product> replaceSingleProduct(@PathVariable("productID") Long productID, @RequestBody ProductDto productDto) throws NotFoundException {
        Optional<Product> productOptional = selfProductService.replaceSingleProduct(productID, productDto);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found.");
        }
        return ResponseEntity.accepted().body(productOptional.get());
    }

    @DeleteMapping("/{productID}")
    public ResponseEntity<Product> deleteSingleProduct(@PathVariable("productID") Long productID) throws NotFoundException {
        Optional<Product> productOptional = selfProductService.deleteSingleProduct(productID);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productOptional.get());
    }
}
