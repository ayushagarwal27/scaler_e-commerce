package org.example.scaler_e_commerce.clients.fakeStoreApi;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class StoreProductClient {

    private final RestTemplateBuilder restTemplateBuilder;

    public StoreProductClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


    public ResponseEntity<ProductDto[]> getAllProducts() {
        return restTemplateBuilder.build().getForEntity("https://fakestoreapi.com/products", ProductDto[].class);
    }


    public ResponseEntity<ProductDto> getSingleProduct(Long productID) {
        return restTemplateBuilder.build().getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class, productID);
    }


    public ResponseEntity<ProductDto> addNewProduct(ProductDto productDto) {
        return restTemplateBuilder.build().postForEntity("https://fakestoreapi.com/products", productDto, ProductDto.class);
    }


    public ResponseEntity<ProductDto> updateSingleProduct(Long productID, ProductDto productDto) {
        return requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                productDto,
                ProductDto.class,
                productID
        );
    }


    public ResponseEntity<ProductDto> replaceSingleProduct(Long productID, ProductDto productDto) {
        return requestForEntity(HttpMethod.PUT, "https://fakestoreapi.com/products/{id}", productDto, ProductDto.class, productID);
    }


    public ResponseEntity<ProductDto> deleteSingleProduct(Long productID) {
        return requestForEntity(HttpMethod.DELETE, "https://fakestoreapi.com/products/{id}", null, ProductDto.class, productID);
    }
}
