package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.clients.StoreCategoryClient;
import org.example.scaler_e_commerce.dtos.ProductDto;
import org.example.scaler_e_commerce.models.Category;
import org.example.scaler_e_commerce.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    StoreCategoryClient storeCategoryClient;

    CategoryServiceImpl(StoreCategoryClient storeCategoryClient) {
        this.storeCategoryClient = storeCategoryClient;
    }

    private Category convertResponseCategoryToCategoryModel(String cate) {
        Category category = new Category();
        category.setName(cate);
        return category;
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
    public Optional<List<Category>> getAllCategories() {
        ResponseEntity<String[]> categoriesResponse = storeCategoryClient.getAllCategories();
        List<Category> categoryList = new ArrayList<>();
        if (categoriesResponse.getBody() == null) {
            return Optional.empty();
        }
        for (String cate : categoriesResponse.getBody()) {
            categoryList.add(convertResponseCategoryToCategoryModel(cate));
        }
        return Optional.of(categoryList);
    }

    @Override
    public Optional<List<Product>> getAllProductsByCategory(String categoryName) {
        ResponseEntity<ProductDto[]> productResponse = storeCategoryClient.getAllProductsByCategory(categoryName);
        List<Product> productList = new ArrayList<>();
        if (productResponse.getBody() == null) {
            return Optional.empty();
        }
        for (ProductDto pr : productResponse.getBody()) {
            productList.add(convertProductDtoToProductModel(pr));
        }
        return Optional.of(productList);
    }
}
