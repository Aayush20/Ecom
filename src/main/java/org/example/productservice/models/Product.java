package org.example.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity(name = "products")
public class Product extends BaseModel{

    private String description;
    private double price;
    private int quantity;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}
