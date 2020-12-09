package ru.asmisloff.spring.ht07.repositories;

import ru.asmisloff.spring.ht07.data.ProductData;
import ru.asmisloff.spring.ht07.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDataRepository extends JpaRepository<Product, Long> {

    @Query("select new ru.asmisloff.spring.ht07.data.ProductData(p.id, p.title, p.brandName, p.price, p.createDate, p.modifyDate) from Product p")
    List<ProductData> findAllProductData();

}
