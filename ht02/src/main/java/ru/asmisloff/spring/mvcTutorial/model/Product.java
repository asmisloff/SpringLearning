package ru.asmisloff.spring.mvcTutorial.model;

public class Product {

    private Integer id;
    private String name;
    private String description;
    private String brand;
    private double price;

    public Product(Integer id, String name, String description, String brand, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public Product setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }
    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }
    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }
    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getBrand() {
        return brand;
    }
    public Product setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
