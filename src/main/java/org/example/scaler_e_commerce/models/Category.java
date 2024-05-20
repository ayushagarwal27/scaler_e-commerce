package org.example.scaler_e_commerce.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;
    private String description;
    @JsonBackReference
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> productList;
}
