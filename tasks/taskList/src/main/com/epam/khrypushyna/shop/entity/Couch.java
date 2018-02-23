package com.epam.khrypushyna.shop.entity;

import com.epam.khrypushyna.shop.creator.reflection.FieldMark;

import java.io.Serializable;

public class Couch extends Furniture implements Serializable {

    private String material;

    public Couch() {
    }

    public Couch(int price, boolean availability, String material) {
        super(price, availability);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    @FieldMark(mark = "furniture.material")
    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Couch couch = (Couch) o;

        return material != null ? material.equals(couch.material) : couch.material == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (material != null ? material.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Couch - id = " + getId() +
                ", price = " + getPrice() +
                ", availability = " + isAvailability() +
                ", material = " + material;
    }
}
