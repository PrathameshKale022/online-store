package com.ekfcstore.onlinestore.products.entity;

import com.ekfcstore.onlinestore.ratings.entity.Rating;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private String title;
    private String category;
    private String price;
    private double average_rating;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rating> ratings;


}
