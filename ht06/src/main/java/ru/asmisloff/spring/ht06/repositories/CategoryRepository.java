package ru.asmisloff.spring.ht06.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.asmisloff.spring.ht06.entities.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String categoryName);
}
