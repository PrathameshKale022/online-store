package com.ekfcstore.onlinestore.products.repo;

import com.ekfcstore.onlinestore.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByTitleAsc();

    List<Product> findAllByOrderByTitleDesc();


    List<Product> findAllByOrderByPriceAsc();

    List<Product> findAllByOrderByPriceDesc();


    List<Product> findAllByCategory(String category);
}
