package io.lekitech;

import java.math.BigInteger;

public class NumToLezgi {
    /**
     * Retrieves the name of a compound based on the provided number.
     * This method is a placeholder and currently returns an empty string.
     *
     * @param number The number for which to retrieve the compound name.
     * @return The name of the compound.
     */
    public static String getCompound(BigInteger number) {
        return "";
    }

    /**
     * Retrieves the name of either an atomic element or a compound based on the provided number.
     * If the provided number corresponds to an atomic element, its name is returned.
     * If the provided number does not correspond to an atomic element, the method attempts to retrieve
     * the name of a compound by calling the getCompound method.
     *
     * @param number The number for which to retrieve the atomic element or compound name.
     * @return The name of the atomic element or compound.
     */
    public static String getAtomicOrCompound(BigInteger number) {
        String result = Constants.getNameByNumber(number);
        return result != null ? result : getCompound(number);
    }

    /**
     * Converts the given number to its Lezgi representation.
     * If the provided number is null, an IllegalArgumentException is thrown.
     *
     * @param number The number to be converted to Lezgi representation.
     * @return The Lezgi representation of the given number.
     * @throws IllegalArgumentException If the provided number is null.
     */
    public static String numToLezgi(BigInteger number) {
        if (number == null) {
            throw new IllegalArgumentException("Provided value is null");
        }
        boolean isNegative = number.signum() == -1;
        number = number.abs();
        String numberName = Constants.getNameByNumber(number);
        return isNegative ? Constants.MINUS + " " + numberName
                : numberName;
    }
}
