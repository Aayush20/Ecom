package org.example.productservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity(name = "categories")
public class Category extends BaseModel {
    private String description;
}
