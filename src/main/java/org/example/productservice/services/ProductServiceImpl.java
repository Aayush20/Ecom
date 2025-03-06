package org.example.productservice.services;


import org.example.productservice.models.Product;
import org.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service("dbProductService")
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
