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

@Service
public class ProductServiceImpl implements ProductService {
    private final RestTemplateBuilder restTemplateBuilder;
    private RestTemplateBuilder templateBuilder;

    public ProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private Product convertProductDtoToProduct(ProductDto productDto) {
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
    public List<Product> getAllProducts() {
        templateBuilder = new RestTemplateBuilder();
        ResponseEntity<ProductDto[]> products = templateBuilder.build().getForEntity("https://fakestoreapi.com/products", ProductDto[].class);
        List<Product> productList = new ArrayList<>();
        for (ProductDto pr : products.getBody()) {
            productList.add(convertProductDtoToProduct(pr));
        }
        return productList;
    }

    @Override
    public Product getSingleProduct(Long productID) {
        templateBuilder = new RestTemplateBuilder();
        ResponseEntity<ProductDto> product = templateBuilder.build().getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, productID);
        return convertProductDtoToProduct(product.getBody());
    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        templateBuilder = new RestTemplateBuilder();
        ResponseEntity<ProductDto> product = templateBuilder.build().postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);
        return convertProductDtoToProduct(product.getBody());
    }

    @Override
    public Product updateSingleProduct(Long productID, ProductDto productDto) {
        ResponseEntity<ProductDto> product = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                productDto,
                ProductDto.class,
                productID
        );
        return convertProductDtoToProduct(product.getBody());
    }

    @Override
    public Product replaceSingleProduct(Long productID, ProductDto productDto) {
        ResponseEntity<ProductDto> product = requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products/{id}", productDto, ProductDto.class, productID);
        return convertProductDtoToProduct(product.getBody());
    }

    @Override
    public Product deleteSingleProduct(Long productID) {
        ResponseEntity<ProductDto> product = requestForEntity(HttpMethod.DELETE, "https://fakestoreapi.com/products/{id}", null, ProductDto.class, productID);
        Product product1 = convertProductDtoToProduct(product.getBody());
        product1.setDeleted(true);
        return product1;
    }
}
