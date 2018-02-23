package com.epam.khrypushyna.task3;

public class VendorCodeStringLength {

    private String someString;

    VendorCodeStringLength(String someString) {
        this.someString = someString;
    }

    @Override
    public int hashCode() {
        return createCustomHashByStringLength(someString);

    }

    private int createCustomHashByStringLength(String someString) {
        return someString.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VendorCodeStringLength that = (VendorCodeStringLength) o;

        return someString.equals(that.someString);
    }
}
