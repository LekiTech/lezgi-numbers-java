package io.lekitech;

import java.math.BigInteger;
import java.util.List;

public class LezgiNumbers {
    /**
     * Converts a BigInteger to a Lezgi representation.
     *
     * @param num The number to be converted.
     * @return The Lezgi representation of the given number.
     */

    public static String numToLezgi(BigInteger num) {
        return NumToLezgi.numToLezgi(num);
    }

    /**
     * Converts a BigInteger number to its Lezgi language representation.
     *
     * @param num The BigInteger number to be converted.
     * @return The Lezgi language representation of the given number.
     * @throws IllegalArgumentException if the provided number is null.
     */
    public static List<String> numToLezgiList(BigInteger num) {
        return NumToLezgi.numToLezgiList(num);
    }

    /**
     * Converts a Lezgi numeral string representation to a BigInteger.
     *
     * @param num a string representing a Lezgi numeral
     * @return the numerical value of the Lezgi numeral as a BigInteger
     * @throws IllegalArgumentException if the provided value is null, not a valid Lezgi numeral,
     *                                  or does not match the expected format
     */
    public static BigInteger lezgiToNum(String num) {
        return LezgiToNum.lezgiToNum(num);
    }
}
