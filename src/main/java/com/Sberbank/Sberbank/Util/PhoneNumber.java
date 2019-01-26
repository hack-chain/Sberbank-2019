package com.Sberbank.Sberbank.Util;

public final class PhoneNumber {
    private final int area;   // area code (3 digits)
    private final int first;   // exchange  (4 digits)
    private final int second;    // extension (2 digits)
    private final int third;    // extension (2 digits)


    public PhoneNumber(int area, int first, int second, int third) {
        this.area = area;
        this.first = first;
        this.second = second;
        this.third = third;
    }

    // how you're supposed to implement equals
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        PhoneNumber that = (PhoneNumber) y;
        return (this.area == that.area) && (this.first == that.first) &&
                                           (this.second == that.second) &&
                                           (this.third == that.third);
    }

    public String toString() {
        return String.format("8-(%03d)-%04d-%02d-%02d", area, first, second, third);
    }
}
