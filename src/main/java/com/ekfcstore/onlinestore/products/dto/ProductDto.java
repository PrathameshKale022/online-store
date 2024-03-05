package com.ekfcstore.onlinestore.products.dto;

import com.ekfcstore.onlinestore.ratings.dto.RatingDto;
import com.ekfcstore.onlinestore.ratings.entity.Rating;
import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@ToString
public class ProductDto {

    private Long product_id;
    private String title;
    private String category;
    private String price;
    private double average_rating;
    private List<RatingDto> ratings;
}
