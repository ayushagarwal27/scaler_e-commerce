package org.example.scaler_e_commerce.controllers;

import org.example.scaler_e_commerce.authenticationClient.AuthenticationClient;
import org.example.scaler_e_commerce.authenticationClient.dtos.RoleDto;
import org.example.scaler_e_commerce.authenticationClient.dtos.SessionStatus;
import org.example.scaler_e_commerce.authenticationClient.dtos.ValidateResponseDto;
import org.example.scaler_e_commerce.dtos.ProductDto;
import org.example.scaler_e_commerce.exceptions.NotFoundException;
import org.example.scaler_e_commerce.models.Product;
import org.example.scaler_e_commerce.services.SelfProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final SelfProductService selfProductService;
    private final AuthenticationClient authenticationClient;

    ProductController(SelfProductService selfProductService, AuthenticationClient authenticationClient) {
        this.selfProductService = selfProductService;
        this.authenticationClient = authenticationClient;
    }


    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(@Nullable @RequestHeader("AUTH_TOKEN") String token) throws NotFoundException {

//        Check if token exits
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        ValidateResponseDto response = authenticationClient.validate(token);

//        Check if token is valid
        if (response.getSessionStatus().equals(SessionStatus.INVALID)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

//        check if user has permission

        boolean isAdmin = false;
        for (RoleDto role : response.getUserDto().getRoles()) {
            if (role.getName().equals("ADMIN")) {
                isAdmin = true;
            }
        }

        if (!isAdmin) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

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
