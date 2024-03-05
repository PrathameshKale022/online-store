package com.ekfcstore.onlinestore.ratings.repo;

import com.ekfcstore.onlinestore.products.entity.Product;
import com.ekfcstore.onlinestore.ratings.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findRatingByProduct(Product product);
}
