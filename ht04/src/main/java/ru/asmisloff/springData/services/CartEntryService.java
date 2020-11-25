package ru.asmisloff.springData.services;


import ru.asmisloff.springData.entity.Cart;
import ru.asmisloff.springData.entity.CartEntry;
import ru.asmisloff.springData.entity.Product;

import java.util.List;

public interface CartEntryService {

    CartEntry get(Long id);
    List<CartEntry> getAll();
    void save(CartEntry cartEntry);
    void remove(CartEntry cartEntry);
    CartEntry addProduct(Cart cart, Product product, Integer quantity);
}
