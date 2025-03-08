package org.example.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    //name = "category_name" to stay consistent with the db migration version.
    @Column(nullable = false, unique = true, name = "category_name")
    private String name;
    private String description;
    // mappedBy is used to specify the field in the Product class that maps the relationship to avoid duplicate mapping
    //Cascade is not recommended for better control of the operations
    @OneToMany(mappedBy = "category",cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Product> products;

    //By default, the FetchType is LAZY, which means that the associated entities are not fetched until they are
    // accessed to avoid unnecessary queries/joins. FetchType.EAGER is used to fetch the associated entities immediately.
    @OneToMany(fetch = FetchType.EAGER)
    //FetchMode.JOIN is used to fetch the associated entities in a single query
    //FetchMode.SELECT is used to fetch the associated entities in separate queries
    @Fetch(FetchMode.JOIN)
    private List<Product> featuredProducts;
    private int count_of_products;
}
