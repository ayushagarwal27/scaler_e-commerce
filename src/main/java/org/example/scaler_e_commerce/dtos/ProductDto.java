package org.example.scaler_e_commerce.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
