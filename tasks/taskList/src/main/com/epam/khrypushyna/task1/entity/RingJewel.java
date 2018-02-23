package com.epam.khrypushyna.task1.entity;


public class RingJewel extends Jewel{

    private int size;

    public RingJewel() {
    }

    public RingJewel(String material, int price, boolean availability, int size) {
        super(material, price, availability);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RingJewel ringJewel = (RingJewel) o;

        return size == ringJewel.size;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + size;
        return result;
    }

    @Override
    public String toString() {
        return "RingJewel{" +
                "size=" + size +
                ", " + super.toString();
    }
}
