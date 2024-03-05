package com.ekfcstore.onlinestore.products.service.impl;

import com.ekfcstore.onlinestore.products.dto.ProductDto;
import com.ekfcstore.onlinestore.products.entity.Product;
import com.ekfcstore.onlinestore.products.repo.ProductRepository;
import com.ekfcstore.onlinestore.products.service.ProductService;
import com.ekfcstore.onlinestore.ratings.entity.Rating;
import com.ekfcstore.onlinestore.ratings.repo.RatingRepository;
import com.ekfcstore.onlinestore.user.dto.UserDto;
import com.ekfcstore.onlinestore.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;
    final ModelMapper modelMapper;
    final UserService userService;
    final RatingRepository ratingRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService,RatingRepository ratingRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product saveProduct = productRepository.save(product);
        ProductDto savedproductdto = modelMapper.map(saveProduct, ProductDto.class);
        return savedproductdto;
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map((product) -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findProductByProductId(Long id) {
        Product product = productRepository.findById(id).orElse(new Product());
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    @Override
    public List<ProductDto> findAllSortedByPriceAscending() {
        List<Product> products = productRepository.findAllByOrderByPriceAsc();
        return products.stream().map((product) -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<ProductDto> findAllSortedByPriceDescending() {
        List<Product> products = productRepository.findAllByOrderByPriceDesc();
        return products.stream().map((product) -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllSortedByTitleAscending() {
        List<Product> products = productRepository.findAllByOrderByTitleAsc();
        return products.stream().map((product) -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllSortedByTitleDescending() {
        List<Product> products = productRepository.findAllByOrderByTitleDesc();
        return products.stream().map((product) -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsSortedByRatingHighestToLowest() {
        // Implement logic to retrieve products sorted by average rating (highest to lowest)
        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "average_rating"));
        return products.stream().map((product) -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllProductsByCategory(String category) {
        List<Product> products = productRepository.findAllByCategory(category);
        return products.stream().map((product) -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void rateProduct(Long productId, String username, int ratingValue) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            // Assuming you have a UserService or UserRepository to fetch user details
            // Replace UserService with your actual user service or repository
            UserDto user = userService.getUserByUserName(username);
            if (user != null) {
                Rating rating = new Rating();
                rating.setProduct(product);
                rating.setUser_id(user.getUser_id());
                rating.setRating_value(ratingValue);
                ratingRepository.save(rating);
                updateAverageRating(productId);
            } else {
                throw new RuntimeException("User not found.");
            }
        } else {
            throw new RuntimeException("Product not found.");
        }
    }

    // Method to update average rating for a product
    private void updateAverageRating(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            List<Rating> ratings = ratingRepository.findRatingByProduct(product);
            double totalRating = 0;
            int count = 0;
            for (Rating rating : ratings) {
                totalRating += rating.getRating_value();
                count++;
            }
            if (count > 0) {
                double averageRating = totalRating / count;
                product.setAverage_rating(averageRating);
                productRepository.save(product);
            }
        }
    }


    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }


}
