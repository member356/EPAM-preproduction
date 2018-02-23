package com.epam.khrypushyna.shop.entity;

import java.util.Objects;

public class Product {

    private int id;
    private int builderId;
    private int categoryId;
    private int area;
    private int price;

    public Product() {
    }

    public Product(int builderId, int categoryId, int area, int price) {
        this.builderId = builderId;
        this.categoryId = categoryId;
        this.area = area;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuilderId() {
        return builderId;
    }

    public void setBuilderId(int builderId) {
        this.builderId = builderId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                builderId == product.builderId &&
                categoryId == product.categoryId &&
                area == product.area &&
                price == product.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, builderId, categoryId, area, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", builderId=" + builderId +
                ", categoryId=" + categoryId +
                ", area=" + area +
                ", price=" + price +
                '}';
    }
}
