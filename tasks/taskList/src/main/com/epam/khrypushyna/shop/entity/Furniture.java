package com.epam.khrypushyna.shop.entity;

import com.epam.khrypushyna.shop.creator.reflection.FieldMark;

import java.io.Serializable;

public class Furniture implements Serializable{

    private int id;
    private int price;
    private boolean availability;

    public Furniture() {
    }

    public Furniture(int price, boolean availability) {
        this.price = price;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    @FieldMark(mark = "furniture.price")
    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    @FieldMark(mark = "furniture.availability")
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Furniture furniture = (Furniture) o;

        if (id != furniture.id) return false;
        if (price != furniture.price) return false;
        return availability == furniture.availability;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + price;
        result = 31 * result + (availability ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(", price=").append(price);
        sb.append(", availability=").append(availability);
        sb.append('}');
        return sb.toString();
    }
}
