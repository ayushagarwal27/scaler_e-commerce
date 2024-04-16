package org.example.scaler_e_commerce.services;

import org.example.scaler_e_commerce.dtos.ProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceImpl implements ProductService {
    private final RestTemplate template = new RestTemplate();

    @Override
    public ProductDto[] getAllProducts() {
        ProductDto[] products = template.getForObject("https://fakestoreapi.com/products", ProductDto[].class);
        return products;
    }

    @Override
    public ProductDto getSingleProduct(Long productID) {
        ProductDto product = template.getForObject("https://fakestoreapi.com/products/" + productID, ProductDto.class);
        return product;
    }

    @Override
    public ProductDto addSingleProduct(ProductDto productDto) {
        ProductDto product = template.postForObject("https://fakestoreapi.com/products", productDto, ProductDto.class);
        return product;
    }

    @Override
    public String updateSingleProduct(Long productID, ProductDto productDto) {
        try {
            template.put("https://fakestoreapi.com/products/" + productID, productDto, ProductDto.class);
            return "Successfully Updated!";
        } catch (Exception err) {
            return "Error :(";
        }
    }

    @Override
    public String deleteSingleProduct(Long productID) {
        try {
            template.delete("https://fakestoreapi.com/products/" + productID, ProductDto.class);
            return "Successfully Deleted";
        } catch (Exception err) {
            return "Error";
        }
    }
}
