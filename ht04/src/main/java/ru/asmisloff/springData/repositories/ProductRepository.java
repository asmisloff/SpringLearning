package ru.asmisloff.springData.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.asmisloff.springData.data.ProductData;
import ru.asmisloff.springData.entity.Category;
import ru.asmisloff.springData.entity.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceGreaterThanEqual(Double price);
    List<Product> findAllByPriceGreaterThanOrderByPriceDesc(Double price);

    Product findByName(String name);

    List<Product> queryAllByCategory(Category category);
    Page<Product> queryAllByCategory(Category category, Pageable pageable);
    @Query("SELECT NEW ru.asmisloff.springData.data.ProductData(p.id, p.name, p.quantity, p.price, c.name) " +
            "FROM Product p " +
            "JOIN p.category c " +
            "WHERE c.name = :category_name")
    List<ProductData> queryAllProductDataByCategory(
            @Param("category_name") String categoryName);

    Optional<Product> findFirstByOrderByPriceAsc();
    Optional<Product> findFirstByCategory_OrderByPriceAsc(Category category);
    Optional<Product> findFirstByCategory_OrderByPriceDesc(Category category);

}
