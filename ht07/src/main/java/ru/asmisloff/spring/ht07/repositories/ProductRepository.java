package ru.asmisloff.spring.ht07.repositories;

import ru.asmisloff.spring.ht07.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("SELECT p FROM Product p WHERE p.price = (SELECT max(price) FROM Product)")
    List<Product> getProductByMaxPrice();
}
