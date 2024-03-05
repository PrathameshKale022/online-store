package com.ekfcstore.onlinestore.ratings.dto;

import com.ekfcstore.onlinestore.products.entity.Product;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RatingDto {

    private Long rating_id;
    private Integer rating_value;
    private String user_id;
    private Long product_id;
}
