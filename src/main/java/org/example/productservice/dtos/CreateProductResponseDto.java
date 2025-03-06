package org.example.productservice.dtos;

import lombok.Data;
import org.example.productservice.models.Product;

@Data
public class CreateProductResponseDto {
    private String description;

    public static CreateProductResponseDto fromProduct(Product product) {
        CreateProductResponseDto createProductResponseDto = new CreateProductResponseDto();
        createProductResponseDto.setDescription(product.getDescription());
        return createProductResponseDto;
    }
}
