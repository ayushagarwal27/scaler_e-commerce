package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.clients.fakeStoreApi.StoreProductClient;
import org.example.scaler_e_commerce.dtos.ProductDto;
import org.example.scaler_e_commerce.models.Category;
import org.example.scaler_e_commerce.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductServiceImpl implements ProductService {
    StoreProductClient storeProductClient;
    //    In memory cache
    HashMap<Long, Product> productMap = new HashMap<>();

    ProductServiceImpl(StoreProductClient storeProductClient) {
        this.storeProductClient = storeProductClient;
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
        ResponseEntity<ProductDto[]> products = storeProductClient.getAllProducts();
        List<Product> productList = new ArrayList<>();
        if (products.getBody() == null) {
            return Optional.empty();
        }
        for (ProductDto pr : products.getBody()) {
            productList.add(convertProductDtoToProductModel(pr));
        }
        return Optional.of(productList);
    }

    @Override
    public Optional<Product> getSingleProduct(Long productID) {
        if (productMap.containsKey(productID)) {
            return Optional.of(productMap.get(productID));
        }
        ResponseEntity<ProductDto> product = storeProductClient.getSingleProduct(productID);

        if (product.getBody() == null) {
            return Optional.empty();
        }
        Product productRes = convertProductDtoToProductModel(product.getBody());
        productMap.put(productRes.getId(), productRes);
        return Optional.of(productRes);
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        ResponseEntity<ProductDto> product = storeProductClient.addNewProduct(productDto);
        return convertProductDtoToProductModel(product.getBody());
    }

    @Override
    public Optional<Product> updateSingleProduct(Long productID, ProductDto productDto) {
        ResponseEntity<ProductDto> product = storeProductClient.updateSingleProduct(productID, productDto);
        if (product.getBody() == null) {
            return Optional.empty();
        }
        return Optional.of(convertProductDtoToProductModel(product.getBody()));
    }

    @Override
    public Optional<Product> replaceSingleProduct(Long productID, ProductDto productDto) {
        ResponseEntity<ProductDto> product = storeProductClient.replaceSingleProduct(productID, productDto);
        if (product.getBody() == null) {
            return Optional.empty();
        }
        return Optional.of(convertProductDtoToProductModel(product.getBody()));
    }

    @Override
    public Optional<Product> deleteSingleProduct(Long productID) {
        ResponseEntity<ProductDto> product = storeProductClient.deleteSingleProduct(productID);
        if (product.getBody() == null) {
            return Optional.empty();
        }

        Product product1 = convertProductDtoToProductModel(product.getBody());
        product1.setDeleted(true);
        return Optional.of(convertProductDtoToProductModel(product.getBody()));
    }
}
