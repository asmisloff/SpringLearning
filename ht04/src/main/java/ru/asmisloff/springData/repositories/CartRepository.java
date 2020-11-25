package ru.asmisloff.springData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.asmisloff.springData.entity.Cart;
import ru.asmisloff.springData.entity.Product;
import ru.asmisloff.springData.entity.User;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByCode(String code);

    @Query("SELECT с FROM Cart с WHERE с.user = :user")
    Cart findCartByUser(@Param("user") User user);

    @Query("SELECT c FROM Cart c JOIN c.cartEntryList ce WHERE ce.product = :product")
    List<Cart> findAllCartsByProduct(@Param("product") Product product);
}

