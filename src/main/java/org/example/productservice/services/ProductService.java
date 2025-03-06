package org.example.productservice.services;

import org.example.productservice.models.Product;

public interface ProductService {
    Product getProductById(int id);
    Product createProduct(Product product);
}
