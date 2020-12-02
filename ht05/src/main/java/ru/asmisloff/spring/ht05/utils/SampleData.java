package ru.asmisloff.spring.ht05.utils;

import ru.asmisloff.spring.ht05.entities.Customer;
import ru.asmisloff.spring.ht05.entities.Order;
import ru.asmisloff.spring.ht05.entities.Product;
import ru.asmisloff.spring.ht05.repositories.CustomerRepository;
import ru.asmisloff.spring.ht05.repositories.OrderRepository;
import ru.asmisloff.spring.ht05.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SampleData {

    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public SampleData(CustomerRepository customerRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @PostConstruct
    public void init() {

        List<Product> products = Stream.of(
                new Product("Orange", 50),
                new Product("Lemon", 70),
                new Product("Lime", 20),
                new Product("Mango", 110),
                new Product("Apple", 95),
                new Product("Pineapple", 76),
                new Product("Avocado", 123),
                new Product("Chicken", 123)
        ).collect(Collectors.toList());
        productRepository.saveAll(products);

        List<Customer> customers = Stream.of(
                new Customer("Alex"),
                new Customer("Alena"),
                new Customer("Ivan"),
                new Customer("Mary Vanna")
        ).collect(Collectors.toList());
        customerRepository.saveAll(customers);

        for (int i = 0; i < 10; ++i) {
            Order order = new Order();
            int prodNumber = ThreadLocalRandom.current().nextInt(products.size());
            int custNumber = ThreadLocalRandom.current().nextInt(customers.size());
            Product product = products.get(prodNumber);
            Customer customer = customers.get(custNumber);
            order.setCurrentPrice(product.getPrice());
            order.setCustomer(customer);
            order.setProduct(product);
            order.setCode(String.format("%04d", i));
            orderRepository.save(order);
        }
    }
}
