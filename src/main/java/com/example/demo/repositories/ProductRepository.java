package com.example.demo.repositories;

import com.example.demo.entities.ProductModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

}
