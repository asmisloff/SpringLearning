package ru.asmisloff.springData.services;

import ru.asmisloff.springData.entity.Category;
import ru.asmisloff.springData.entity.Product;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();
    void save(Category category);
}
