package ru.asmisloff.spring.ht06.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.asmisloff.spring.ht06.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);

}
