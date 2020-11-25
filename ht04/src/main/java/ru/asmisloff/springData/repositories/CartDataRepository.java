package ru.asmisloff.springData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.asmisloff.springData.data.CartData;
import ru.asmisloff.springData.entity.Cart;

@Repository
public interface CartDataRepository extends JpaRepository<Cart, Long> {

    @Query("select new ru.asmisloff.springData.data.CartData(c) from Cart c JOIN c.user u where c.code = :code")
    CartData findByCode(@Param("code") String code);

}

