package com.epam.khrypushyna.task1.entity;

public class Jewel {

    private String material;
    private int price;
    private boolean availability;

    public Jewel() {
    }

    public Jewel(String material, int price, boolean availability) {
        this.material = material;
        this.price = price;
        this.availability = availability;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jewel jewel = (Jewel) o;

        if (price != jewel.price) return false;
        if (availability != jewel.availability) return false;
        return material != null ? material.equals(jewel.material) : jewel.material == null;
    }

    @Override
    public int hashCode() {
        int result = material != null ? material.hashCode() : 0;
        result = 31 * result + price;
        result = 31 * result + (availability ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return " material='" + material + '\'' +
                ", price=" + price +
                ", availability=" + availability;
    }
}