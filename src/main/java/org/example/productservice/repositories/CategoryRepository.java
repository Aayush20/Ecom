package org.example.productservice.repositories;

import org.example.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryById(Long id);

    Optional<Category> findCategoryByName(String name);

    Optional<Category> findByName(String name);

//    List<Category> findByIdGreaterThanAndNameGreaterThan(
//            Long id, String name);

    Category save(Category category);
}
