package ru.asmisloff.springData.data;

import ru.asmisloff.springData.entity.Product;

public class ProductData {

    private Long id;
    private String name;
    private Long quantity;
    private Double price;
    private String categoryName;

    public ProductData(Long id, String name, Long quantity, Double price, String categoryName) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
