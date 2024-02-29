package io.lekitech;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final BigInteger MILLION =
            new BigInteger("1000000");
    public static final BigInteger BILLION =
            new BigInteger("1000000000");
    public static final BigInteger TRILLION =
            new BigInteger("1000000000000");
    public static final BigInteger QUADRILLION =
            new BigInteger("1000000000000000");
    public static final BigInteger QUINTILLION =
            new BigInteger("1000000000000000000");
    public static final BigInteger SEXTILLION =
            new BigInteger("1000000000000000000000");
    public static final BigInteger SEPTILLION =
            new BigInteger("1000000000000000000000000");
    public static final BigInteger OCTILLION =
            new BigInteger("1000000000000000000000000000");
    public static final BigInteger NONILLION =
            new BigInteger("1000000000000000000000000000000");

    private static final Map<BigInteger, String> NUMBER_MAP = new HashMap<>();

    static {
        NUMBER_MAP.put(BigInteger.valueOf(0), "нул");
        NUMBER_MAP.put(BigInteger.valueOf(1), "сад");
        NUMBER_MAP.put(BigInteger.valueOf(2), "кьвед");
        NUMBER_MAP.put(BigInteger.valueOf(3), "пуд");
        NUMBER_MAP.put(BigInteger.valueOf(4), "кьуд");
        NUMBER_MAP.put(BigInteger.valueOf(5), "вад");
        NUMBER_MAP.put(BigInteger.valueOf(6), "ругуд");
        NUMBER_MAP.put(BigInteger.valueOf(7), "ирид");
        NUMBER_MAP.put(BigInteger.valueOf(8), "муьжуьд");
        NUMBER_MAP.put(BigInteger.valueOf(9), "кIуьд");
        NUMBER_MAP.put(BigInteger.valueOf(10), "цIуд");
        NUMBER_MAP.put(BigInteger.valueOf(20), "къад");
        NUMBER_MAP.put(BigInteger.valueOf(40), "яхцIур");
        NUMBER_MAP.put(BigInteger.valueOf(100), "виш");
        NUMBER_MAP.put(BigInteger.valueOf(1000), "агъзур");
        NUMBER_MAP.put(MILLION, "миллион");
        NUMBER_MAP.put(BILLION, "миллиард");
        NUMBER_MAP.put(TRILLION, "триллион");
        NUMBER_MAP.put(QUADRILLION, "квадриллион");
        NUMBER_MAP.put(QUINTILLION, "квинтиллион");
        NUMBER_MAP.put(SEXTILLION, "секстиллион");
        NUMBER_MAP.put(SEPTILLION, "септиллион");
        NUMBER_MAP.put(OCTILLION, "октиллион");
        NUMBER_MAP.put(NONILLION, "нониллион");
    }
    /**
     * Retrieves the name associated with the given number from the NUMBER_MAP.
     *
     * @param number The number for which to retrieve the associated name.
     * @return The name associated with the given number, or null if the number is not found in the map.
     */
    public static String getNameByNumber(BigInteger number) {
        return NUMBER_MAP.get(number);
    }

    public static final String MINUS = "минус";
}
