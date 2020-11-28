package ru.asmisloff.springData.main;

import org.javatuples.Pair;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.asmisloff.springData.config.AppConfig;
import ru.asmisloff.springData.entity.Cart;
import ru.asmisloff.springData.entity.Category;
import ru.asmisloff.springData.entity.Product;
import ru.asmisloff.springData.entity.User;
import ru.asmisloff.springData.services.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MainCart {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

//        CartService cartService = applicationContext.getBean("cartService", CartService.class);
//        CartEntryService cartEntryService = applicationContext.getBean("cartEntryService", CartEntryService.class);
        ProductService productService = applicationContext.getBean("productService", ProductService.class);
//        UserService userService = applicationContext.getBean("userService", UserService.class);
        CategoryService categoryService = applicationContext.getBean("categoryService", CategoryService.class);

//        User user1 = new User("Alex");
//        User user2 = new User("Alena");
//
//        userService.save(user1);
//        userService.save(user2);

        Category fruits = new Category();
        fruits.setName("fruits");
        Category vegetables = new Category();
        vegetables.setName("vegetables");
        categoryService.save(fruits);
        categoryService.save(vegetables);

        Product product1 = new Product("Apple", 100L, 15.0);
        Product product2 = new Product("Orange", 200L, 30.0);
        Product product3 = new Product("Mango", 30L, 25.0);
        Product product4 = new Product("Lemon", 60L, 50.0);
        Product product5 = new Product("Pineapple", 15L, 45.0);

        Stream.of(product1, product2, product3, product4, product5).forEach(p -> {
            p.setCategory(fruits);
            productService.save(p);
        });

        Stream.of(
                Pair.with("Огурец", 10.0), Pair.with("Кабачок", 20.0), Pair.with("Лук", 5.0),
                Pair.with("Морковь", 15.0), Pair.with("Капуста", 25.0)
        ).forEach(pair -> {
            Product product = new Product(pair.getValue0(), 100L, pair.getValue1());
            product.setCategory(vegetables);
            productService.save(product);
        });

        List<Category> allCategories = categoryService.getAll();
        Map<String, List<Product>> catalog = new HashMap<>();
        allCategories.forEach(category -> {
            catalog.put(category.getName(), productService.findAllByCategory(category));
        });

        System.out.println("---------------------------------------------\n");

        System.out.println(catalog);
        System.out.println(productService.findAllProductDataByCategory(vegetables.getName()));
        System.out.printf("Cheapest fruit: %s\n", productService.findCheapestProductInCategory(fruits).toString());
        System.out.printf("Cheapest product: %s\n", productService.findCheapestProduct().toString());
        System.out.printf(
                "Cheapest and the most expensive vegetables: %s\n",
                productService
                        .findCheapestAndMostExpensiveProductsInCategory(vegetables)
                        .toString()
        );
        Page<Product> page = productService.findAllByCategory(
                fruits,
                PageRequest.of(0, 2, Sort.by("name").ascending())
        );
        System.out.println(page);
        System.out.println(page.getContent());

        System.out.println("---------------------------------------------\n");


//        Cart cart1 = new Cart();
//        cart1.setCode("0001");
//        cart1.setUser(user1);
//        cartService.save(cart1);
//
//        Cart cart2 = new Cart();
//        cart2.setCode("0002");
//        cart2.setUser(user2);
//        cartService.save(cart2);
//
//        cartEntryService.addProduct(cart1, product1, 2);
//        cartEntryService.addProduct(cart1, product2, 5);
//        cartEntryService.addProduct(cart1, product3, 15);
//        cartEntryService.addProduct(cart1, product4, 15);

        //cartEntryService.addProduct(cart2, product3, 2);
//        cartEntryService.addProduct(cart2, product4, 5);
//        cartEntryService.addProduct(cart2, product5, 3);

//        Cart cartAlex = cartService.getByCode("0001");
//        System.out.println("cartAlex = " + cartAlex);
//
//        List<Product> productList1 = productService.findAllByPriceGreaterThan(30.0);
//        System.out.println("productList1 = " + productList1);
//
//        List<Product> productList2 = productService.findAllByPriceGreaterThanOrderByPriceDesc(25.0);
//        System.out.println(productList2);
//
//        Cart cartAlena = cartService.findCartByUser(user2);
//        System.out.println("cartAlena = " + cartAlena);
//
//        Product product = productService.findByName("Lemon");
//
//        List<Cart> carts = cartService.findAllCartsByProduct(product);
//        System.out.println("carts = " + carts);

    }
}
