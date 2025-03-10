package org.example.productservice.controllers;

import org.example.productservice.dtos.products.CreateProductRequestDto;
import org.example.productservice.dtos.products.CreateProductResponseDto;
import org.example.productservice.exceptions.ErrorResponseDto;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    // Injects the value of the 'productServiceType' property from the application.properties file instead of @Qualifier
    @Value("${productServiceType}")
    private String productServiceType;

    private ProductService productService;

    public ProductController(@Qualifier("dbProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        Product product = productService.createProduct(createProductRequestDto.toProduct());
        return CreateProductResponseDto.fromProduct(product);
    }

    //Controller's own Exception Handlers
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return e.getMessage() + "Something went wrong!";
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseDto handleRuntimeException(RuntimeException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setStatus("500");
        return errorResponseDto;
    }

}
