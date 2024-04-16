package org.example.scaler_e_commerce.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private Category category;
}
