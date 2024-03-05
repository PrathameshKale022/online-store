package com.ekfcstore.onlinestore.ratings.entity;


import com.ekfcstore.onlinestore.products.entity.Product;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rating_id;
    private Integer rating_value;
    private Long user_id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
