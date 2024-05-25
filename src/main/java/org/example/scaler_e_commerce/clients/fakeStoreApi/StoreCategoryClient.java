package org.example.scaler_e_commerce.clients.fakeStoreApi;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class StoreCategoryClient {
    private final RestTemplateBuilder templateBuilder;

    StoreCategoryClient(RestTemplateBuilder templateBuilder) {
        this.templateBuilder = new RestTemplateBuilder();
    }

    public ResponseEntity<String[]> getAllCategories() {
        return templateBuilder.build().getForEntity("https://fakestoreapi.com/products/categories", String[].class);
    }


    public ResponseEntity<ProductDto[]> getAllProductsByCategory(String categoryName) {
        return templateBuilder.build().getForEntity("https://fakestoreapi.com/products/category/{categoryName}", ProductDto[].class, categoryName);
    }
}
