package org.example.productservice.repositories;

import org.example.productservice.models.Product;
import org.example.productservice.projections.ProductNameAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Product with ID: Update or Insert
    Product save(Product product);

    void delete(Product product);

    //what and how
    //https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    //https://docs.spring.io/spring-data/jpa/reference/repositories/query-keywords-reference.html
    Product findProductById(Long id);

    //Query using attributes of attribute
    List<Product> findAllByCategory_nameEquals(String name);

    //Custom JPA Query
    @Query("select p from Product p " +
            " where p.category.description = :categoryDescription")
    List<Product> randomFunction(@Param("categoryDescription") String categoryDescription);


    @Query("select p from Product p where p.id > :idGreaterThan")
    List<Product> randomFunction2(@Param("idGreaterThan") Long idGreaterThan);

    //Native SQL Query for complex queries and query optimization
    @Query(value = "select * from product p join category c where c.id = p.id", nativeQuery = true)
    List<Product> randomSQLFunction(@Param("id") Long id);

    @Query(
            value = CustomQueries.GET_PRODUCTS_WITH_SUBCATEGORY_NAME,
            nativeQuery = true
    )
    List<Product> repeatedFunction();

    @Query(value = "select name, description from product where id = :id", nativeQuery = true)
    ProductNameAndDescription getProductTitleAndDescSQL(@Param("id") Long id);



    List<Product> findByTitleContaining(String query);

    Page<Product> findAllByTitleContainingAndCategory_Id(
            String title, Long categoryId, Pageable pageable
    );
}
