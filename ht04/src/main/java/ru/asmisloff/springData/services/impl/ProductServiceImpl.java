package ru.asmisloff.springData.services.impl;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asmisloff.springData.data.ProductData;
import ru.asmisloff.springData.entity.Category;
import ru.asmisloff.springData.entity.Product;
import ru.asmisloff.springData.repositories.ProductRepository;
import ru.asmisloff.springData.services.ProductService;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Product get(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void remove(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return productRepository.queryAllByCategory(category);
    }

    @Override
    public List<ProductData> findAllProductDataByCategory(String categoryName) {
        return productRepository.queryAllProductDataByCategory(categoryName);
    }

    @Override
    public Page<Product> findAllByCategory(Category category, Pageable pageable) {
        return productRepository.queryAllByCategory(category, pageable);
    }

    @Override
    public List<Product> findAllByPriceGreaterThan(Double price) {
        return productRepository.findAllByPriceGreaterThanEqual(price);
    }

    @Override
    public List<Product> findAllByPriceGreaterThanOrderByPriceDesc(Double price) {
        return productRepository.findAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public Product findCheapestProductInCategory(Category category) {
        return productRepository.findFirstByCategory_OrderByPriceAsc(category).orElse(null);
    }

    @Override
    public Pair<Product, Product> findCheapestAndMostExpensiveProductsInCategory(Category category) {
        return Pair.with(
                productRepository.findFirstByCategory_OrderByPriceAsc(category).orElse(null),
                productRepository.findFirstByCategory_OrderByPriceDesc(category).orElse(null)
        );
    }

    @Override
    public Product findCheapestProduct() {
        return productRepository.findFirstByOrderByPriceAsc().orElse(null);
    }

}
