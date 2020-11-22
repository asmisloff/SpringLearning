package ru.asmisloff.hibernateTutorial;

import javax.persistence.*;

@Entity
@Table(name = "sellings")
public class Selling {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column(name = "product_id")
    private final Long productId;

    @Column(name = "customer_id")
    private final Long customerId;

    @Column
    private final double price;

    public Selling(Long customerId, Long productId, double Price) {
        this.customerId = customerId;
        this.productId = productId;
        price = Price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Selling{" +
                "id=" + id +
                ", customer=" + customerId +
                ", product=" + productId +
                ", price=" + price +
                '}';
    }
}
