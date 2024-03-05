package com.ekfcstore.onlinestore.products.controller;

import com.ekfcstore.onlinestore.products.dto.ProductDto;
import com.ekfcstore.onlinestore.products.entity.Product;
import com.ekfcstore.onlinestore.products.service.ProductService;
import com.ekfcstore.onlinestore.ratings.dto.RatingDto;
import com.ekfcstore.onlinestore.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/productModule")
public class ProductController {
    final ProductService productService;
    final ModelMapper modelMapper;
    final JwtTokenUtil jwtTokenUtil;

    public ProductController(ProductService productService, ModelMapper modelMapper,JwtTokenUtil jwtTokenUtil) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @PostMapping("/admin/products")
    public ResponseEntity<?> addProduct(@RequestHeader("Authorization") String token,@RequestBody ProductDto productDto) {
        try {
            ProductDto createdproductdto = productService.addProduct(productDto);

            return new ResponseEntity<>(createdproductdto, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Create product.");
        }
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<?> updateProduct(@RequestHeader("Authorization") String token,@PathVariable Long productId, @RequestBody Product updatedProduct) {
        try {
            ProductDto existingProduct = productService.findProductByProductId(productId);
            if (existingProduct == null) {
                return ResponseEntity.notFound().build();
            }
            ProductDto productupdated = modelMapper.map(existingProduct, ProductDto.class);
            // Update other fields as needed

            productService.addProduct(productupdated);
            return ResponseEntity.ok(existingProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update product.");
        }
    }

    // Delete a product
    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<?> deleteProduct(@RequestHeader("Authorization") String token,@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok().body("Product Deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product.");
        }
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(@RequestHeader("Authorization") String token) {
        try {
            String jwtToken = token.substring(7); // Remove "Bearer " prefix
            // Validate and extract username from the token (if needed)
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            log.info("username===> "+username);
            List<ProductDto> products = productService.findAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to sort product.");
        }
    }

    @GetMapping(path = "/products",params = "category")
    public ResponseEntity<?> getProductsByCategory(@RequestHeader("Authorization") String token,@RequestParam String category) {
        try {
            List<ProductDto> products = productService.findAllProductsByCategory(category);
            return ResponseEntity.ok(products);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to sort product.");
        }
    }

    // Sort products by title, price, or average rating
    @GetMapping(path = "/products",params = "sort")
    public ResponseEntity<?> getSortedProducts(@RequestHeader("Authorization") String token,@RequestParam String sort) {
        try {
            List<ProductDto> sortedProducts;
            switch (sort) {
                case "titleA":
                    sortedProducts = productService.findAllSortedByTitleAscending();
                    break;
                case "titleD":
                    sortedProducts = productService.findAllSortedByTitleDescending();
                    break;
                case "priceA":
                    sortedProducts = productService.findAllSortedByPriceAscending();
                    break;
                case "priceD":
                    sortedProducts = productService.findAllSortedByPriceDescending();
                    break;
                case "rating":
                    sortedProducts = productService.getProductsSortedByRatingHighestToLowest();
                    break;
                default:
                    sortedProducts = productService.findAllProducts();
                    break;
            }
            return ResponseEntity.ok(sortedProducts);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to sort product.");
        }
    }


    // Give rating to a product (only accessible to authenticated users)
    @PostMapping("/ratings")
    public ResponseEntity<?> rateProduct(@RequestHeader("Authorization") String token,@RequestBody RatingDto ratingDto, Principal principal) {
        try {
            String username = principal.getName();
            productService.rateProduct(ratingDto.getProduct_id(), username, ratingDto.getRating_value());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to rate product.");
        }
    }
}
