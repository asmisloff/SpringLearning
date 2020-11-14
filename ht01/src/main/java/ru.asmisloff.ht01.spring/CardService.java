package ru.asmisloff.ht01.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardService {

    private ProductRepository prepo;
    private final List<Product> prods;

    public CardService() {
        prods = new ArrayList<>();
    }

    public CardService add(int id) {
        prods.add(prepo.get(id));
        return this;
    }

    public void removeAll(int id) {
        prods.removeIf(p -> p.getId() == id);
    }

    public Product remove(int id) {

        for (int i = 0; i < prods.size(); i++) {
            Product p = prods.get(i);
            if (p.getId() == id) {
                prods.remove(i);
                return p;
            }
        }

        return  null;
    }

    public double total() {
        return prods.stream().mapToDouble(Product::getPrice).sum();
    }

    @Autowired
    public void setProductRepository(ProductRepository prepo) {
        this.prepo = prepo;
    }

    @Override
    public String toString() {
        return "CardService{" +
                "prods=" + prods +
                '}' +
                "; total = " + total();
    }
}
