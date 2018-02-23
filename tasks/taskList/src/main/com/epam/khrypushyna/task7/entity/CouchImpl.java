package com.epam.khrypushyna.task7.entity;

import java.util.Objects;

public class CouchImpl implements ICouch {

    private int price;
    private boolean availability;
    private String material;

    public CouchImpl() {
    }

    public CouchImpl(int price, boolean availability, String material) {
        this.price = price;
        this.availability = availability;
        this.material = material;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouchImpl couch = (CouchImpl) o;
        return price == couch.price &&
                availability == couch.availability &&
                Objects.equals(material, couch.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, availability, material);
    }

    @Override
    public String toString() {
        return "CouchImpl{" +
                "price=" + price +
                ", availability=" + availability +
                ", material='" + material + '\'' +
                '}';
    }
}
