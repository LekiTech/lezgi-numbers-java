package io.lekitech;

import java.math.BigInteger;

public class NumToLezgi {
    public static String getCompound(BigInteger number) {
        return "";
    }

    public static String getAtomicOrCompound(BigInteger number) {
        String result = Constants.getNameByNumber(number);
        return result != null ? result : getCompound(number);
    }

    public static String numToLezgi(BigInteger number) {
        if (number == null) {
            throw new IllegalArgumentException("Provided value is null");
        }
        boolean isNegative = number.signum() == -1;
        number = number.abs();
        String numberName = Constants.getNameByNumber(number);
        return isNegative ? Constants.MINUS + " " + numberName :
                numberName;
    }
}
