package ru.asmisloff.spring.ht08.repositories;

import ru.asmisloff.spring.ht08.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoriesByCode(String code);
}
