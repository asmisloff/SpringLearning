package ru.asmisloff.springData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.asmisloff.springData.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
