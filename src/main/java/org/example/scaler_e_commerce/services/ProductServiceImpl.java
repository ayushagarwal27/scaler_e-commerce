package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.example.scaler_e_commerce.models.Category;
import org.example.scaler_e_commerce.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final RestTemplateBuilder restTemplateBuilder;

    public ProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
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

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public Optional<List<Product>> getAllProducts() {
        ResponseEntity<ProductDto[]> products = restTemplateBuilder.build().getForEntity("https://fakestoreapi.com/products", ProductDto[].class);
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
        ResponseEntity<ProductDto> product = restTemplateBuilder.build().getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, productID);

        if (product.getBody() == null) {
            return Optional.empty();
        }
        return Optional.of(convertProductDtoToProductModel(product.getBody()));
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        ResponseEntity<ProductDto> product = restTemplateBuilder.build().postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);

        return convertProductDtoToProductModel(product.getBody());
    }

    @Override
    public Optional<Product> updateSingleProduct(Long productID, ProductDto productDto) {
        ResponseEntity<ProductDto> product = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                productDto,
                ProductDto.class,
                productID
        );
        if (product.getBody() == null) {
            return Optional.empty();
        }
        return Optional.of(convertProductDtoToProductModel(product.getBody()));
    }

    @Override
    public Optional<Product> replaceSingleProduct(Long productID, ProductDto productDto) {
        ResponseEntity<ProductDto> product = requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products/{id}", productDto, ProductDto.class, productID);
        if (product.getBody() == null) {
            return Optional.empty();
        }
        return Optional.of(convertProductDtoToProductModel(product.getBody()));
    }

    @Override
    public Optional<Product> deleteSingleProduct(Long productID) {
        ResponseEntity<ProductDto> product = requestForEntity(HttpMethod.DELETE, "https://fakestoreapi.com/products/{id}", null, ProductDto.class, productID);
        if (product.getBody() == null) {
            return Optional.empty();
        }

        Product product1 = convertProductDtoToProductModel(product.getBody());
        product1.setDeleted(true);
        return Optional.of(convertProductDtoToProductModel(product.getBody()));
    }
}
