package com.example.product_microservice.Service;

import com.example.product_microservice.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product saveProduct(Product product);

    Optional<Product> getProductById(Long id);

    void deleteProduct(Long id);

    List<Product> getAllProducts();
}
