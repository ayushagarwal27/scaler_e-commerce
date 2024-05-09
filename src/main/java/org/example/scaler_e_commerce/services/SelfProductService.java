package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.example.scaler_e_commerce.models.Category;
import org.example.scaler_e_commerce.models.Product;
import org.example.scaler_e_commerce.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements ProductService {
    ProductRepository productRepository;

    SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private Product convertProductDtoToProductModel(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Optional<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return Optional.of(products);
    }

    public Optional<Product> getSingleProduct(Long productID) {
        Optional<Product> product = productRepository.findById(productID);
        return product;
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public Optional<Product> updateSingleProduct(Long productID, ProductDto productDto) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> replaceSingleProduct(Long productID, ProductDto productDto) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> deleteSingleProduct(Long productID) {
        return Optional.empty();
    }
}
