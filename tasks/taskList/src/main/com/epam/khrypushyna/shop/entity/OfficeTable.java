package com.epam.khrypushyna.shop.entity;

import com.epam.khrypushyna.shop.creator.reflection.FieldMark;

import java.io.Serializable;

public class OfficeTable extends Desk implements Serializable{

    private boolean cases;

    public OfficeTable() {
    }

    public OfficeTable(int price, boolean availability, int height, boolean cases) {
        super(price, availability, height);
        this.cases = cases;
    }

    public boolean isCases() {
        return cases;
    }

    @FieldMark(mark = "furniture.cases")
    public void setCases(boolean cases) {
        this.cases = cases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OfficeTable that = (OfficeTable) o;

        return cases == that.cases;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (cases ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Office Table - id = " + getId() +
                ", price = " + getPrice() +
                ", availability = " + isAvailability() +
                ", height = " + getHeight() +
                ", cases = " + cases;
    }
}
