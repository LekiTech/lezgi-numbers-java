package io.lekitech;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

final class Constants {
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

    private static final Map<BigInteger, String> ATOMIC = new HashMap<>();

    static {
        ATOMIC.put(BigInteger.valueOf(0), "нул");
        ATOMIC.put(BigInteger.valueOf(1), "сад");
        ATOMIC.put(BigInteger.valueOf(2), "кьвед");
        ATOMIC.put(BigInteger.valueOf(3), "пуд");
        ATOMIC.put(BigInteger.valueOf(4), "кьуд");
        ATOMIC.put(BigInteger.valueOf(5), "вад");
        ATOMIC.put(BigInteger.valueOf(6), "ругуд");
        ATOMIC.put(BigInteger.valueOf(7), "ирид");
        ATOMIC.put(BigInteger.valueOf(8), "муьжуьд");
        ATOMIC.put(BigInteger.valueOf(9), "кIуьд");
        ATOMIC.put(BigInteger.valueOf(10), "цIуд");
        ATOMIC.put(BigInteger.valueOf(20), "къад");
        ATOMIC.put(BigInteger.valueOf(40), "яхцIур");
        ATOMIC.put(BigInteger.valueOf(100), "виш");
        ATOMIC.put(BigInteger.valueOf(1000), "агъзур");
        ATOMIC.put(MILLION, "миллион");
        ATOMIC.put(BILLION, "миллиард");
        ATOMIC.put(TRILLION, "триллион");
        ATOMIC.put(QUADRILLION, "квадриллион");
        ATOMIC.put(QUINTILLION, "квинтиллион");
        ATOMIC.put(SEXTILLION, "секстиллион");
        ATOMIC.put(SEPTILLION, "септиллион");
        ATOMIC.put(OCTILLION, "октиллион");
        ATOMIC.put(NONILLION, "нониллион");
    }

    public static final String MINUS = "минус";

    private static final Map<String, Numeral> NUMERALS = new HashMap<>();
    private static final AllowedRange ALLOWED_FH =
            new AllowedRange("виш", 100, BigInteger.valueOf(Long.MAX_VALUE));
    private static final AllowedRange ALLOWED_FT =
            new AllowedRange("агъзур", 1000, BigInteger.valueOf(Long.MAX_VALUE));

    static {
        NUMERALS.put("нул", new Numeral(BigInteger.ZERO, false));
        NUMERALS.put("сад", new Numeral(BigInteger.valueOf(1), false));
        NUMERALS.put("кьвед", new Numeral(BigInteger.TWO, false));
        NUMERALS.put("кьве", new Numeral(BigInteger.valueOf(2), true, ALLOWED_FH));
        NUMERALS.put("пуд", new Numeral(BigInteger.valueOf(3), false, ALLOWED_FH));
        NUMERALS.put("кьуд", new Numeral(BigInteger.valueOf(4), false, ALLOWED_FH));
        NUMERALS.put("вад", new Numeral(BigInteger.valueOf(5), false, ALLOWED_FH));
        NUMERALS.put("ругуд", new Numeral(BigInteger.valueOf(6), false, ALLOWED_FH));
        NUMERALS.put("ирид", new Numeral(BigInteger.valueOf(7), false, ALLOWED_FH));
        NUMERALS.put("муьжуьд", new Numeral(BigInteger.valueOf(8), false, ALLOWED_FH));
        NUMERALS.put("кIуьд", new Numeral(BigInteger.valueOf(9), false, ALLOWED_FH));
        NUMERALS.put("цIуд", new Numeral(BigInteger.TEN, false, ALLOWED_FT));
        NUMERALS.put("цIусад", new Numeral(BigInteger.valueOf(11), false, ALLOWED_FT));
        NUMERALS.put("цIикьвед", new Numeral(BigInteger.valueOf(12), false, ALLOWED_FT));
        NUMERALS.put("цIипуд", new Numeral(BigInteger.valueOf(13), false, ALLOWED_FT));
        NUMERALS.put("цIикьуд", new Numeral(BigInteger.valueOf(14), false, ALLOWED_FT));
        NUMERALS.put("цIувад", new Numeral(BigInteger.valueOf(15), false, ALLOWED_FT));
        NUMERALS.put("цIуругуд", new Numeral(BigInteger.valueOf(16), false, ALLOWED_FT));
        NUMERALS.put("цIерид", new Numeral(BigInteger.valueOf(17), false, ALLOWED_FT));
        NUMERALS.put("цIемуьжуьд", new Numeral(BigInteger.valueOf(18), false, ALLOWED_FT));
        NUMERALS.put("цIекIуьд", new Numeral(BigInteger.valueOf(19), false, ALLOWED_FT));
        NUMERALS.put("къад", new Numeral(BigInteger.valueOf(20), false, ALLOWED_FT));
        NUMERALS.put("къанни", new Numeral(BigInteger.valueOf(20), true,
                new AllowedRange("сад", 1, BigInteger.valueOf(19))));
        NUMERALS.put("яхцIур", new Numeral(BigInteger.valueOf(40), false, ALLOWED_FT));
        NUMERALS.put("яхцIурни", new Numeral(BigInteger.valueOf(40), true,
                new AllowedRange("сад", 1, BigInteger.valueOf(19))));
        NUMERALS.put("пудкъад", new Numeral(BigInteger.valueOf(60), false, ALLOWED_FT));
        NUMERALS.put("пудкъанни", new Numeral(BigInteger.valueOf(60), true,
                new AllowedRange("сад", 1, BigInteger.valueOf(19))));
        NUMERALS.put("кьудкъад", new Numeral(BigInteger.valueOf(80), false, ALLOWED_FT));
        NUMERALS.put("кьудкъанни", new Numeral(BigInteger.valueOf(80), true,
                new AllowedRange("сад", 1, BigInteger.valueOf(19))));
        NUMERALS.put("виш", new Numeral(BigInteger.valueOf(100), false, ALLOWED_FT));
        NUMERALS.put("вишни", new Numeral(BigInteger.valueOf(100), true,
                new AllowedRange("сад", 1, BigInteger.valueOf(99))));
        NUMERALS.put("агъзур", new Numeral(BigInteger.valueOf(1000), false));
        NUMERALS.put("агъзурни", new Numeral(BigInteger.valueOf(1000), true,
                new AllowedRange("сад", 1, BigInteger.valueOf(1000))));
        NUMERALS.put("миллион", new Numeral(MILLION, false));
        NUMERALS.put("миллионни", new Numeral(MILLION, true,
                new AllowedRange("сад", 1, MILLION)));
        NUMERALS.put("миллиард", new Numeral(BILLION, false));
        NUMERALS.put("миллиардни", new Numeral(BILLION, true,
                new AllowedRange("сад", 1, BILLION)));
        NUMERALS.put("триллион", new Numeral(TRILLION, false));
        NUMERALS.put("триллионни", new Numeral(TRILLION, true,
                new AllowedRange("сад", 1, TRILLION)));
        NUMERALS.put("квадриллион", new Numeral(QUADRILLION, false));
        NUMERALS.put("квадриллионни", new Numeral(QUADRILLION, true,
                new AllowedRange("сад", 1, QUADRILLION)));
        NUMERALS.put("квинтиллион", new Numeral(QUINTILLION, false));
        NUMERALS.put("квинтиллионни", new Numeral(QUINTILLION, true,
                new AllowedRange("сад", 1, QUINTILLION)));
        NUMERALS.put("секстиллион", new Numeral(SEXTILLION, false));
        NUMERALS.put("секстиллионни", new Numeral(SEXTILLION, true,
                new AllowedRange("сад", 1, SEXTILLION)));
        NUMERALS.put("септиллион", new Numeral(SEPTILLION, false));
        NUMERALS.put("септиллионни", new Numeral(SEPTILLION, true,
                new AllowedRange("сад", 1, SEPTILLION)));
        NUMERALS.put("октиллион", new Numeral(OCTILLION, false));
        NUMERALS.put("октиллионни", new Numeral(OCTILLION, true,
                new AllowedRange("сад", 1, OCTILLION)));
        NUMERALS.put("нониллион", new Numeral(NONILLION, false));
        NUMERALS.put("нониллионни", new Numeral(NONILLION, true,
                new AllowedRange("сад", 1, NONILLION)));
    }

    public static class Numeral {
        public BigInteger value;
        public boolean requiresNext;
        public AllowedRange allowedNext;

        public Numeral(BigInteger value, boolean requiresNext) {
            this.value = value;
            this.requiresNext = requiresNext;
        }

        Numeral(BigInteger value, boolean requiresNext, AllowedRange allowedNext) {
            this.value = value;
            this.requiresNext = requiresNext;
            this.allowedNext = allowedNext;
        }
    }

    public static class AllowedRange {
        public String minStr;
        public int min;
        public BigInteger max;

        public AllowedRange(String minStr, int min, BigInteger max) {
            this.minStr = minStr;
            this.min = min;
            this.max = max;
        }
    }

    public static String getAtomicValueByKey(BigInteger number) {
        return ATOMIC.get(number);
    }

    public static Numeral getNumeralByKey(String name) {
        return NUMERALS.get(name);
    }

    public static BigInteger getNumeralValueByKey(String name) {
        return getNumeralByKey(name).value;
    }

    public static boolean isValidKeyByNumeral(String name) {
        return NUMERALS.containsKey(name);
    }
}
