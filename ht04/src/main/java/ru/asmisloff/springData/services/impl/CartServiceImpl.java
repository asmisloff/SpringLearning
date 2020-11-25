package ru.asmisloff.springData.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asmisloff.springData.entity.Cart;
import ru.asmisloff.springData.entity.Product;
import ru.asmisloff.springData.entity.User;
import ru.asmisloff.springData.repositories.CartRepository;
import ru.asmisloff.springData.services.CartService;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Cart get(Long id) {
        return cartRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getByCode(String code) {
        return cartRepository.findByCode(code);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void remove(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public Cart findCartByUser(User user) {
        return cartRepository.findCartByUser(user);
    }

    @Override
    public List<Cart> findAllCartsByProduct(Product product) {
        return cartRepository.findAllCartsByProduct(product);
    }

}
