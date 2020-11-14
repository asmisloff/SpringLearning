package ru.asmisloff.ht01.spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Component
public class ProductRepository {

    private final List<Product> prods;

    public ProductRepository() {
        prods = new ArrayList<>();
    }

    @PostConstruct
    public void postConstruct() {
        Stream.of(1, 2, 3, 4, 5)
                .forEach(n -> prods.add(new Product(n, "Product #" + n, 10 * n)));
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(prods);
    }

    public Product get(int id) {
        return prods.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

}
