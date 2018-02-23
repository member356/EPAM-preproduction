package com.epam.khrypushyna.task3;

public class VendorCodeStringSymbols {

    private String someString;

    VendorCodeStringSymbols(String someString) {
        this.someString = someString;
    }

    @Override
    public int hashCode() {
        return createCustomHashByStringFirstFourSymbolsSum(someString);
    }

    private int createCustomHashByStringFirstFourSymbolsSum(String someString) {
        int sum = 0;
        int limit = (someString.length() < 4) ? someString.length() : 3;

        for(int i = 0; i <= limit; i++){
            sum = sum + (int)someString.charAt(i);
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VendorCodeStringSymbols that = (VendorCodeStringSymbols) o;

        return someString.equals(that.someString);
    }
}
