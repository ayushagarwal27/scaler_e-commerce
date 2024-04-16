package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final RestTemplate template = new RestTemplate();

    @Override
    public String[] getAllCategories() {
        String[] categories = template.getForObject("https://fakestoreapi.com/products/categories", String[].class);
        return categories;
    }

    @Override
    public ProductDto[] getAllProductsByCategory(String categoryName) {
        ProductDto[] products = template.getForObject("https://fakestoreapi.com/products/category/" + categoryName, ProductDto[].class);
        return products;
    }
}
