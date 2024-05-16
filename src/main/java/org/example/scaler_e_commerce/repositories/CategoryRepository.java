package org.example.scaler_e_commerce.repositories;

import org.example.scaler_e_commerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryByName(String name);
}
