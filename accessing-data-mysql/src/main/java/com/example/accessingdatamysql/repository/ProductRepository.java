package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByPrice(Double price, Pageable pageable);

    List<Product> findAllByCategory(String category, Pageable pageable);
}
