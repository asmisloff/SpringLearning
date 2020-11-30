package ru.asmisloff.springData.services;

import org.javatuples.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.asmisloff.springData.data.ProductData;
import ru.asmisloff.springData.entity.Category;
import ru.asmisloff.springData.entity.Product;

import java.util.List;

public interface ProductService {

    Product get(Long id);

    List<Product> getAll();

    void save(Product product);

    void remove(Product product);

    Product findByName(String name);

    List<Product> findAllByCategory(Category category);
    List<ProductData> findAllProductDataByCategory(String categoryName);

    Page<Product> findAllByCategory(Category category, Pageable pageable);

    List<Product> findAllByPriceGreaterThan(Double price);

    List<Product> findAllByPriceGreaterThanOrderByPriceDesc(Double price);

    Product findCheapestProductInCategory(Category category);

    Pair<Product, Product> findCheapestAndMostExpensiveProductsInCategory(Category category);

    Product findCheapestProduct();
}
