package com.ekfcstore.onlinestore.products.service;

import com.ekfcstore.onlinestore.products.dto.ProductDto;

import java.util.List;

public interface ProductService {


    ProductDto addProduct(ProductDto productDto);

    List<ProductDto> findAllProducts();

    ProductDto findProductByProductId(Long id);

    List<ProductDto> findAllSortedByTitleAscending();

    List<ProductDto> findAllSortedByTitleDescending();

    List<ProductDto> findAllSortedByPriceAscending();

    List<ProductDto> findAllSortedByPriceDescending();

    List<ProductDto> getProductsSortedByRatingHighestToLowest();

    List<ProductDto> findAllProductsByCategory(String category);

    void rateProduct(Long productId, String username, int ratingValue);

    void deleteProduct(Long productId);
}
