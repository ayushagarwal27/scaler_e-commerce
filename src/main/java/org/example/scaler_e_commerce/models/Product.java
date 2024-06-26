package org.example.scaler_e_commerce.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    @JsonManagedReference
    @ManyToOne
    private Category category;
}
