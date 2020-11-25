package ru.asmisloff.springData.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asmisloff.springData.entity.Cart;
import ru.asmisloff.springData.entity.CartEntry;
import ru.asmisloff.springData.entity.Product;
import ru.asmisloff.springData.repositories.CartEntryRepository;
import ru.asmisloff.springData.services.CartEntryService;

import java.util.List;

@Service("cartEntryService")
public class CartEntryServiceImpl implements CartEntryService {

    private CartEntryRepository cartEntryRepository;

    @Autowired
    public void setCartEntryRepository(CartEntryRepository cartEntryRepository) {
        this.cartEntryRepository = cartEntryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public CartEntry get(Long id) {
        return cartEntryRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartEntry> getAll() {
        return cartEntryRepository.findAll();
    }

    @Override
    @Transactional
    public void save(CartEntry cartEntry) {
        cartEntryRepository.save(cartEntry);
    }

    @Override
    @Transactional
    public void remove(CartEntry cartEntry) {
        cartEntryRepository.delete(cartEntry);
    }

    @Override
    @Transactional
    public CartEntry addProduct(Cart cart, Product product, Integer quantity) {
        CartEntry cartEntry = new CartEntry();
        cartEntry.setCart(cart);
        cartEntry.setPrice(product.getPrice());
        cartEntry.setProduct(product);
        cartEntry.setQuantity(quantity);
        return cartEntryRepository.save(cartEntry);
    }

}
