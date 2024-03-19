package io.lekitech;

import java.io.IOException;
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

    /**
     * Converts numerical data from a file into Lezgi text-to-speech (TTS) format.
     *
     * @param filePath the path to the file containing numerical data to be converted.
     * @return a byte array representing the Lezgi TTS format of the numbers found in the file.
     * @throws IOException if an I/O error occurs reading from the file or if the file cannot be found.
     */
    public static byte[] numToLezgiTTS(String filePath) throws IOException {
        return NumToLezgiTTS.numToLezgiTTS(filePath);
    }
}
