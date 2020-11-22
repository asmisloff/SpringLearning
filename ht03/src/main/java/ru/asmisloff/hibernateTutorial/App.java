package ru.asmisloff.hibernateTutorial;

import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    private static EntityManager em = null;

    private static void setup() {
        if (em != null) { return; }

        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        em = factory.createEntityManager();
    }

    private static void test() {
        try {
            EntityTransaction tr = em.getTransaction();
            tr.begin();

            addProduct("Апельсин", 100);
            addProduct("Мандарин", 200);
            addProduct("Кукла", 300);
            addProduct("Шарик", 400);
            addProduct("Ёлка", 1000);

            Stream.of("Вася", "Петя", "Вова", "Миша", "Маша").forEach(App::addCustomer);

            try {
                sell(1L, 2L);
                sell(2L, 3L);
                sell(2L, 1L);
                sell(5L, 5L);
                sell(5L, 3L);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            tr.commit();

            getAll(Product.class).forEach(System.out::println);
            getAll(Customer.class).forEach(System.out::println);
            getAll(Selling.class).forEach(System.out::println);
            System.out.println(buyersOf(5L).map(Customer::getName).collect(Collectors.joining(", ")));
            System.out.println(buyersOf(3L).map(Customer::getName).collect(Collectors.joining(", ")));
            System.out.println(purchasesOf(2L).map(Product::getTitle).collect(Collectors.joining(", ")));
            System.out.println(purchasesOf(5L).map(Product::getTitle).collect(Collectors.joining(", ")));

            deleteAll(Selling.class);
            deleteAll(Product.class);
            deleteAll(Customer.class);

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void main(String[] args) {
        setup();
        test();
    }

    private static void addProduct(String title, double price) {
        EntityTransaction tr = em.getTransaction();
        if (tr.isActive()) {
            tr = null;
        } else {
            tr.begin();
        }
        Product product = new Product(title, price);
        em.persist(product);
        if (tr != null) {
            tr.commit();
        }
    }

    private static void addCustomer(String name) {
        EntityTransaction tr = em.getTransaction();
        if (tr.isActive()) {
            tr = null;
        } else {
            tr.begin();
        }
        Customer customer = new Customer(name);
        em.persist(customer);
        if (tr != null) {
            tr.commit();
        }
    }

    private static void sell(Customer c, Product p) {
        EntityTransaction tr = em.getTransaction();

        if (tr.isActive()) {
            tr = null;
        } else {
            tr.begin();
        }

        Selling s = new Selling(c.getId(), p.getId(), p.getPrice());
        em.persist(s);

        if (tr != null) {
            tr.commit();
        }
    }

    private static void sell(Long customerId, Long productId) throws NullPointerException {
        Customer c = em.find(Customer.class, customerId);
        Product p = em.find(Product.class, productId);
        if (c == null) {
            throw new NullPointerException("customerId = " + customerId + " not found in db");
        }
        if (p == null) {
            throw new NullPointerException("productId = " + productId + " not found in db");
        }
        sell(c, p);
    }

    public static <T> Stream<T> getAll(Class<T> type) {
        CriteriaQuery<T> q = em.getCriteriaBuilder().createQuery(type);
        q.from(type);
        return em.createQuery(q).getResultStream();
    }

    public static <T> void deleteByIds(Class<T> type, long... ids) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<T> delQuery = cb.createCriteriaDelete(type);
        Root<T> root = delQuery.from(type);
        delQuery.where(root.get("id")
                .in(Arrays.stream(ids)
                        .boxed()
                        .collect(Collectors.toList())));
        em.createQuery(delQuery).executeUpdate();

        tr.commit();
    }

    public static <T> void deleteAll(Class<T> type) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();

        CriteriaDelete<T> delQuery = em.getCriteriaBuilder().createCriteriaDelete(type);
        delQuery.from(type);
        em.createQuery(delQuery).executeUpdate();

        tr.commit();
    }

    public static Stream<Customer> buyersOf(Long id) {
        List<Long> customerIds =  em.createQuery("SELECT s FROM Selling s WHERE s.productId = " + id, Selling.class)
                .getResultStream()
                .map(Selling::getCustomerId)
                .distinct()
                .collect(Collectors.toList());
        CriteriaQuery<Customer> q = em.getCriteriaBuilder().createQuery(Customer.class);
        Root<Customer> r = q.from(Customer.class);
        q.where(r.get("id").in(customerIds));
        return em.createQuery(q).getResultStream();
    }

    public static Stream<Product> purchasesOf(Long id) {
        List<Long> productIds =  em.createQuery("SELECT s FROM Selling s WHERE s.customerId = " + id, Selling.class)
                .getResultStream()
                .map(Selling::getProductId)
                .distinct()
                .collect(Collectors.toList());
        CriteriaQuery<Product> q = em.getCriteriaBuilder().createQuery(Product.class);
        Root<Product> r = q.from(Product.class);
        q.where(r.get("id").in(productIds));
        return em.createQuery(q).getResultStream();
    }

}
