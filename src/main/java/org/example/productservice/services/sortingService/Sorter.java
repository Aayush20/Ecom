package org.example.productservice.services.sortingService;

import org.example.productservice.models.Product;

import java.util.List;

public interface Sorter {
    List<Product> sort(List<Product> products);
}
