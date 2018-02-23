package com.epam.khrypushyna.task1.entity;

import com.sun.javafx.UnmodifiableArrayList;

import java.util.Collections;
import java.util.List;

public class ChainletJewel extends Jewel{

    private int chainLength;

    public ChainletJewel() {
    }

    public ChainletJewel(String material, int price, boolean availability, int chainLength) {
        super(material, price, availability);
        this.chainLength = chainLength;
    }

    public int getChainLength() {
        return chainLength;
    }

    public void setChainLength(int chainLength) {
        this.chainLength = chainLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ChainletJewel that = (ChainletJewel) o;

        return chainLength == that.chainLength;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + chainLength;
        return result;
    }

    @Override
    public String toString() {
        return "ChainletJewel{" +
                "chainLength=" + chainLength +
                "} " + super.toString();
    }
}
