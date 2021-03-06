package ru.asmisloff.spring.ht06.services;

import ru.asmisloff.spring.ht06.entities.Product;
import ru.asmisloff.spring.ht06.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public Page<Product> findAllByCategoryId(Long categoryId, int page, int size) {
        return productRepository.findAllByCategoryId(categoryId, PageRequest.of(page, size));
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void saveOrUpdate(Product product) {
        productRepository.save(product);
    }
}
