package com.epam.khrypushyna.shop.entity;

import com.epam.khrypushyna.shop.creator.reflection.FieldMark;

import java.io.Serializable;

public class Desk extends Furniture implements Serializable{

    private int height;

    public Desk() {
    }

    public Desk(int price, boolean availability, int height) {
        super(price, availability);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    @FieldMark(mark = "furniture.height")
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Desk desk = (Desk) o;

        return height == desk.height;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return "Desk - id = " + getId() +
                ", price = " + getPrice() +
                ", availability = " + isAvailability() +
                ", height = " + height;
    }
}
