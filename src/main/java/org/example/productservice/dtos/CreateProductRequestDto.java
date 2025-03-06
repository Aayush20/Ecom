package org.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice.models.Product;

@Getter
@Setter
public class CreateProductRequestDto {
    String description;

    public Product toProduct() {
        Product product = new Product();
        product.setDescription(this.description);
        return product;
    }
}
