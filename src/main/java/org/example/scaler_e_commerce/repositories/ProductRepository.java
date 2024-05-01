package org.example.scaler_e_commerce.repositories;

import org.example.scaler_e_commerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getProductById(Long id);


}
