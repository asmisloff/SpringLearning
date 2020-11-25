package ru.asmisloff.springData.services;

import ru.asmisloff.springData.entity.Cart;
import ru.asmisloff.springData.entity.Product;
import ru.asmisloff.springData.entity.User;

import java.util.List;

public interface CartService {

    Cart get(Long id);
    Cart getByCode(String code);
    List<Cart> getAll();
    void save(Cart cart);
    void remove(Cart cart);

    Cart findCartByUser(User user);
    List<Cart> findAllCartsByProduct(Product product);
}
