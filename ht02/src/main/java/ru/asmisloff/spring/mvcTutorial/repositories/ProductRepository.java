package ru.asmisloff.spring.mvcTutorial.repositories;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.asmisloff.spring.mvcTutorial.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class ProductRepository {

    private final List<Product> prods;

    public ProductRepository() {
        prods = new ArrayList<>();
    }

    @PostConstruct
    public void postConstruct() {
        Stream.of(1, 2, 3, 4, 5)
                .forEach(n -> prods.add(new Product(n, "Product #" + n, "Товар №" + n, "Acme",10 * n)));
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(prods);
    }

    public Product get(int id) {
        return prods.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void add(Product p) {
        prods.add(p);
    }

    public int generateNewId() {
        int[] ids = prods.stream().mapToInt(Product::getId).sorted().toArray();
        for (int i = 1; ; ++i) {
            if (Arrays.binarySearch(ids, i) < 0) {
                return i;
            }
        }
    }

    public void updateProduct(Product p) {
        this.get(p.getId())
                .setName(p.getName())
                .setDescription(p.getDescription())
                .setBrand(p.getBrand())
                .setPrice(p.getPrice());
    }
}
