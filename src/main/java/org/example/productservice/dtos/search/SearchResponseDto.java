package org.example.productservice.dtos.search;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice.dtos.products.GetProductDto;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class SearchResponseDto {
    private Page<GetProductDto> productsPage;
}
