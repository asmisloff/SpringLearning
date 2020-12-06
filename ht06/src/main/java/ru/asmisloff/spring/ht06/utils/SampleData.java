package ru.asmisloff.spring.ht06.utils;

import org.javatuples.Pair;
import ru.asmisloff.spring.ht06.entities.Customer;
import ru.asmisloff.spring.ht06.entities.Order;
import ru.asmisloff.spring.ht06.entities.Product;
import ru.asmisloff.spring.ht06.entities.Category;
import ru.asmisloff.spring.ht06.repositories.CategoryRepository;
import ru.asmisloff.spring.ht06.repositories.CustomerRepository;
import ru.asmisloff.spring.ht06.repositories.OrderRepository;
import ru.asmisloff.spring.ht06.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

@Component
public class SampleData {

    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private CategoryRepository categoryRepository;

    public SampleData(CustomerRepository customerRepository, ProductRepository productRepository,
                      OrderRepository orderRepository, CategoryRepository categoryRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    public void init() {

        Category fruits = new Category("fruits");
        fruits.setTitle("Фрукты");
        categoryRepository.save(fruits);
        Category vegetables = new Category("vegetables");
        vegetables.setTitle("Овощи");
        categoryRepository.save(vegetables);
        Category meat = new Category("meat");
        meat.setTitle("Мясо");
        categoryRepository.save(meat);

        Product product1 = new Product("Orange", 50.);
        Product product2 = new Product("Lemon", 70.);
        Product product3 = new Product("Lime", 20.);
        Product product4 = new Product("Mango", 110.);
        Product product5 = new Product("Apple", 95.);
        Product product6 = new Product("Pineapple", 76.);
        Stream.of(product1, product2, product3, product4, product5, product6).forEach(p -> p.setCategory(fruits));
        Stream.of(
                Pair.with("Cucumber", 10.0), Pair.with("Squash", 20.0), Pair.with("Onion", 5.0),
                Pair.with("Carrot", 15.0), Pair.with("Cabbage", 25.0)
        ).forEach(pair -> {
            Product product = new Product(pair.getValue0(), pair.getValue1());
            product.setCategory(vegetables);
            productRepository.save(product);
        });

        Customer customer1 = new Customer("Alex");
        Customer customer2 = new Customer("Alena");

        Order order1 = new Order();
        order1.setTotalPrice(product1.getPrice());
        order1.setCustomer(customer1);

        order1.setCode("0001");

        Order order2 = new Order();
        order2.setTotalPrice(product2.getPrice());
        order2.setCustomer(customer2);

        order2.setCode("0002");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);

        customerRepository.save(customer1);
        customerRepository.save(customer2);

        orderRepository.save(order1);
        orderRepository.save(order2);

    }
}
