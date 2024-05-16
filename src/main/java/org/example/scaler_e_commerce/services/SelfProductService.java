package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.example.scaler_e_commerce.models.Category;
import org.example.scaler_e_commerce.models.Product;
import org.example.scaler_e_commerce.repositories.CategoryRepository;
import org.example.scaler_e_commerce.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private Product convertProductDtoToProductModel(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        product.setCategory(category);
        return product;
    }

    @Override
    public Optional<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return Optional.of(products);
    }

    public Optional<Product> getSingleProduct(Long productID) {
        return productRepository.findById(productID);
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        Category category = categoryRepository.getCategoryByName(productDto.getCategory());
        Product product = convertProductDtoToProductModel(productDto, category);
        product = productRepository.save(product);
        return product;
    }

    @Override
    public Optional<Product> updateSingleProduct(Long productID, ProductDto productDto) {
        Optional<Product> product = productRepository.findById(productID);
        return Optional.empty();
    }

    @Override
    public Optional<Product> replaceSingleProduct(Long productID, ProductDto productDto) {
        Category category = categoryRepository.getCategoryByName(productDto.getCategory());
        return Optional.of(productRepository.save(convertProductDtoToProductModel(productDto, category)));
    }

    @Override
    public Optional<Product> deleteSingleProduct(Long productID) {
        Optional<Product> product = productRepository.findById(productID);
        productRepository.deleteById(productID);
        return product;
    }
}
