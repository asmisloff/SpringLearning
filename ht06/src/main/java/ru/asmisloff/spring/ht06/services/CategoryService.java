package ru.asmisloff.spring.ht06.services;

import org.springframework.stereotype.Service;
import ru.asmisloff.spring.ht06.entities.Category;
import ru.asmisloff.spring.ht06.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByName(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }
}
