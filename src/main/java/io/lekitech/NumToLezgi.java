package io.lekitech;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NumToLezgi {
    public static List<BigInteger> separateNumberIntoUnits(BigInteger num) {
        List<BigInteger> result = new ArrayList<>();
        BigInteger ten = BigInteger.valueOf(10);
        BigInteger one = BigInteger.valueOf(1);
        while (num.signum() > 0) {
            result.add(0, num.mod(ten).multiply(one));
            num = num.divide(ten);
            one = one.multiply(ten);
        }
        return groupNumberUnitsToLezgiRange(result, Range.ranges);
    }

    public static List<BigInteger> groupNumberUnitsToLezgiRange(List<BigInteger> arr, List<Range> ranges) {
        List<BigInteger> result = new ArrayList<>();
        for (Range range : ranges) {
            BigInteger sum = BigInteger.valueOf(0);
            List<BigInteger> tempArr = new ArrayList<>();
            for (BigInteger num : arr) {
                if (num.compareTo(range.start) >= 0 && num.compareTo(range.end) < 0) {
                    sum = sum.add(num);
                } else {
                    tempArr.add(num);
                }
            }
            if (!sum.equals(BigInteger.valueOf(0))) {
                result.add(sum);
            }
            arr = tempArr;
        }
        result.addAll(arr);
        return result;
    }

    public static String getTenPlusBase(BigInteger num) {
        if (isBigIntegerLessThan(num, BigInteger.valueOf(10))
                || isBigIntegerGreaterThan(num, BigInteger.valueOf(20))
                || isBigIntegerEqualTo(num, BigInteger.valueOf(20))) {
            throw new IllegalArgumentException("Invalid number");
        }
        String result10 = Constants.getNameByNumber(BigInteger.valueOf(10));
        if (isBigIntegerEqualTo(num, BigInteger.valueOf(10))) {
            return result10;
        }
        String base10 = result10.
                substring(0, result10.length() - 2);
        if (isBigIntegerEqualTo(num, BigInteger.valueOf(11))
                || isBigIntegerEqualTo(num, BigInteger.valueOf(15))
                || isBigIntegerEqualTo(num, BigInteger.valueOf(16))) {
            return base10 + "у";
        } else if (isBigIntegerLessThan(num, BigInteger.valueOf(15))) {
            return base10 + "и";
        }
        return base10 + "е";
    }

    public static String getTwentyPlusBase(BigInteger num) {
        return isBigIntegerEqualTo(num, BigInteger.valueOf(20))
                ? Constants.getNameByNumber(BigInteger.valueOf(20)) : "къанни ";
    }

    public static String getThirtyPlusBase(BigInteger num) {
        return getTwentyPlusBase(num)
                + getTenPlusBase(num.subtract(BigInteger.valueOf(20)));
    }

    public static String getFourtyPlusBase(BigInteger num) {
        return isBigIntegerEqualTo(num, BigInteger.valueOf(40))
                ? Constants.getNameByNumber(BigInteger.valueOf(40)) : "ни ";
    }

    public static String getFiftyPlusBase(BigInteger num) {
        return getFourtyPlusBase(num)
                + getTenPlusBase(num.subtract(BigInteger.valueOf(20)));
    }

    public static String getSixtyPlusBase(BigInteger num) {
        return isBigIntegerEqualTo(num, BigInteger.valueOf(60))
                ? Constants.getNameByNumber(BigInteger.valueOf(3))
                + Constants.getNameByNumber(BigInteger.valueOf(20))
                : Constants.getNameByNumber(BigInteger.valueOf(3))
                + getTwentyPlusBase(num);
    }

    public static String getSeventyPlusBase(BigInteger num) {
        return getSixtyPlusBase(BigInteger.valueOf(60))
                + getTenPlusBase(num.subtract(BigInteger.valueOf(60)));
    }

    public static String getEightyPlusBase(BigInteger num) {
        return isBigIntegerEqualTo(num, BigInteger.valueOf(80))
                ? Constants.getNameByNumber(BigInteger.valueOf(4))
                + Constants.getNameByNumber(BigInteger.valueOf(20))
                : Constants.getNameByNumber(BigInteger.valueOf(4))
                + getTwentyPlusBase(num);
    }

    public static String getNinetyPlusBase(BigInteger num) {
        return getEightyPlusBase(BigInteger.valueOf(81))
                + getTenPlusBase(num.subtract(BigInteger.valueOf(80)));
    }

    public static String getHundredPlusBase(BigInteger num) {
        BigInteger hundred = BigInteger.valueOf(100);
        if (num.mod(hundred).equals(BigInteger.ZERO)) {
            return Constants.getNameByNumber(BigInteger.valueOf(100));
        } else {
            return Constants.getNameByNumber(BigInteger.valueOf(100))
                    + "ни ";
        }
    }

    public static String getHundredPlusNumCount(BigInteger numCount) {
        String atomicName = Constants.getNameByNumber(numCount);
        return numCount.equals(BigInteger.valueOf(2)) ? atomicName.substring(0, atomicName.length() - 1) : atomicName;
    }

    public static String getBetweenHundredAndThousand(BigInteger num, BigInteger followUpNumber) {
        BigInteger hundredsCount;
        if (!num.mod(BigInteger.valueOf(100)).equals(BigInteger.ZERO)) {
            hundredsCount = num.subtract(num.mod(BigInteger.valueOf(100)));
        } else {
            hundredsCount = num.divide(BigInteger.valueOf(100));
        }
        String hundredsCountInLezgi = getHundredPlusNumCount(hundredsCount);
        BigInteger sum = num.add(followUpNumber);
        return hundredsCountInLezgi + " " + getHundredPlusBase(sum);
    }

    public static String getThousandPlusBase(BigInteger num, BigInteger followUpNumber) {
        BigInteger hundred = BigInteger.valueOf(1000);
        if (num.mod(hundred).equals(BigInteger.ZERO)) {
            return Constants.getNameByNumber(BigInteger.valueOf(1000));
        } else {
            return Constants.getNameByNumber(BigInteger.valueOf(1000))
                    + "ни ";
        }
    }

    public static String getBetweenThousandAndMillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger thousandsCount;
        if (!num.mod(BigInteger.valueOf(1000)).equals(BigInteger.ZERO)) {
            thousandsCount = num.subtract(num.mod(BigInteger.valueOf(1000)));
        } else {
            thousandsCount = num.divide(BigInteger.valueOf(1000));
        }
        String thousandsCountInLezgi = getHundredPlusNumCount(thousandsCount);
        if (thousandsCountInLezgi == null) {
            thousandsCountInLezgi = getCompound(thousandsCount);
        }
        String thousandPlusBase = getThousandPlusBase(num, followUpNumber);
        return thousandsCountInLezgi + " " + thousandPlusBase;
    }

    public static String getMillionPlusBase(BigInteger num) {
        BigInteger hundred = Constants.MILLION;
        if (num.mod(hundred).equals(BigInteger.ZERO)) {
            return Constants.getNameByNumber(Constants.MILLION);
        } else {
            return Constants.getNameByNumber(Constants.MILLION)
                    + "ни ";
        }
    }

    public static String getBetweenMillionAndBillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger millionsCount;
        if (!num.mod(Constants.MILLION).equals(BigInteger.ZERO)) {
            millionsCount = num.subtract(num.mod(Constants.MILLION));
        } else {
            millionsCount = num.divide(Constants.MILLION);
        }
        String millionsCountInLezgi = getHundredPlusNumCount(millionsCount);
        if (millionsCountInLezgi == null) {
            millionsCountInLezgi = getCompound(millionsCount);
        }
        BigInteger sum = num.add(followUpNumber);
        String millionPlusBase = getMillionPlusBase(sum);
        return millionsCountInLezgi + " " + millionPlusBase;
    }

    public static String getBillionPlusBase(BigInteger num) {
        BigInteger hundred = Constants.BILLION;
        if (num.mod(hundred).equals(BigInteger.ZERO)) {
            return Constants.getNameByNumber(Constants.BILLION);
        } else {
            return Constants.getNameByNumber(Constants.BILLION)
                    + "ни ";
        }
    }

    public static String getBetweenBillionAndTrillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger billionsCount;
        if (!num.mod(Constants.BILLION).equals(BigInteger.ZERO)) {
            billionsCount = num.subtract(num.mod(Constants.BILLION));
        } else {
            billionsCount = num.divide(Constants.BILLION);
        }
        String billionsCountInLezgi = getHundredPlusNumCount(billionsCount);
        if (billionsCountInLezgi == null) {
            billionsCountInLezgi = getCompound(billionsCount);
        }
        BigInteger sum = num.add(followUpNumber);
        String billionPlusBase = getBillionPlusBase(sum);
        return billionsCountInLezgi + " " + billionPlusBase;
    }

    public static String getTrillionPlusBase(BigInteger num) {
        BigInteger hundred = Constants.TRILLION;
        if (num.mod(hundred).equals(BigInteger.ZERO)) {
            return Constants.getNameByNumber(Constants.TRILLION);
        } else {
            return Constants.getNameByNumber(Constants.TRILLION)
                    + "ни ";
        }
    }

    public static String getBetweenTrillionAndQuadrillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger trillionsCount;
        if (!num.mod(Constants.TRILLION).equals(BigInteger.ZERO)) {
            trillionsCount = num.subtract(num.mod(Constants.TRILLION));
        } else {
            trillionsCount = num.divide(Constants.TRILLION);
        }
        String trillionsCountInLezgi = getHundredPlusNumCount(trillionsCount);
        if (trillionsCountInLezgi == null) {
            trillionsCountInLezgi = getCompound(trillionsCount);
        }
        BigInteger sum = num.add(followUpNumber);
        String trillionPlusBase = getTrillionPlusBase(sum);
        return trillionsCountInLezgi + " " + trillionPlusBase;
    }

    public static String getQuadrillionPlusBase(BigInteger num) {
        BigInteger hundred = Constants.QUADRILLION;
        if (num.mod(hundred).equals(BigInteger.ZERO)) {
            return Constants.getNameByNumber(Constants.QUADRILLION);
        } else {
            return Constants.getNameByNumber(Constants.QUADRILLION)
                    + "ни ";
        }
    }

    public static String getBetweenQuadrillionAndQuintillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger quadrillionsCount;
        if (!num.mod(Constants.QUADRILLION).equals(BigInteger.ZERO)) {
            quadrillionsCount = num.subtract(num.mod(Constants.QUADRILLION));
        } else {
            quadrillionsCount = num.divide(Constants.QUADRILLION);
        }
        String quadrillionsCountInLezgi = getHundredPlusNumCount(quadrillionsCount);
        if (quadrillionsCountInLezgi == null) {
            quadrillionsCountInLezgi = getCompound(quadrillionsCount);
        }
        BigInteger sum = num.add(followUpNumber);
        String quadrillionPlusBase = getQuadrillionPlusBase(sum);
        return quadrillionsCountInLezgi + " " + quadrillionPlusBase;
    }

    public static String getQuintillionPlusBase(BigInteger num) {
        BigInteger hundred = Constants.QUINTILLION;
        if (num.mod(hundred).equals(BigInteger.ZERO)) {
            return Constants.getNameByNumber(Constants.QUINTILLION);
        } else {
            return Constants.getNameByNumber(Constants.QUINTILLION)
                    + "ни ";
        }
    }

    public static String getBetweenQuintillionAndSextillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger quintillionsCount;
        if (!num.mod(Constants.QUINTILLION).equals(BigInteger.ZERO)) {
            quintillionsCount = num.subtract(num.mod(Constants.QUINTILLION));
        } else {
            quintillionsCount = num.divide(Constants.QUINTILLION);
        }
        String quintillionsCountInLezgi = getHundredPlusNumCount(quintillionsCount);
        if (quintillionsCountInLezgi == null) {
            quintillionsCountInLezgi = getCompound(quintillionsCount);
        }
        BigInteger sum = num.add(followUpNumber);
        String quintillionPlusBase = getQuintillionPlusBase(sum);
        return quintillionsCountInLezgi + " " + quintillionPlusBase;
    }

    public static String getSextillionPlusBase(BigInteger num) {
        BigInteger hundred = Constants.SEXTILLION;
        if (num.mod(hundred).equals(BigInteger.ZERO)) {
            return Constants.getNameByNumber(Constants.SEXTILLION);
        } else {
            return Constants.getNameByNumber(Constants.SEXTILLION)
                    + "ни ";
        }
    }

    public static String getBetweenSextillionAndSeptillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger sextillionsCount;
        if (!num.mod(Constants.SEXTILLION).equals(BigInteger.ZERO)) {
            sextillionsCount = num.subtract(num.mod(Constants.SEXTILLION));
        } else {
            sextillionsCount = num.divide(Constants.SEXTILLION);
        }
        String sextillionsCountInLezgi = getHundredPlusNumCount(sextillionsCount);
        if (sextillionsCountInLezgi == null) {
            sextillionsCountInLezgi = getCompound(sextillionsCount);
        }
        BigInteger sum = num.add(followUpNumber);
        String sextillionPlusBase = getSextillionPlusBase(sum);
        return sextillionsCountInLezgi + " " + sextillionPlusBase;
    }

    public static String getSeptillionPlusBase(BigInteger num) {
        BigInteger hundred = Constants.SEPTILLION;
        if (num.mod(hundred).equals(BigInteger.ZERO)) {
            return Constants.getNameByNumber(Constants.SEPTILLION);
        } else {
            return Constants.getNameByNumber(Constants.SEPTILLION)
                    + "ни ";
        }
    }

    public static String getBetweenSeptillionAndOctillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger septillionsCount;
        if (!num.mod(Constants.SEPTILLION).equals(BigInteger.ZERO)) {
            septillionsCount = num.subtract(num.mod(Constants.SEPTILLION));
        } else {
            septillionsCount = num.divide(Constants.SEPTILLION);
        }
        String septillionsCountInLezgi = getHundredPlusNumCount(septillionsCount);
        if (septillionsCountInLezgi == null) {
            septillionsCountInLezgi = getCompound(septillionsCount);
        }
        BigInteger sum = num.add(followUpNumber);
        String septillionPlusBase = getSeptillionPlusBase(sum);
        return septillionsCountInLezgi + " " + septillionPlusBase;
    }

    public static String getOctillionPlusBase(BigInteger num) {
        BigInteger hundred = Constants.OCTILLION;
        if (num.mod(hundred).equals(BigInteger.ZERO)) {
            return Constants.getNameByNumber(Constants.OCTILLION);
        } else {
            return Constants.getNameByNumber(Constants.OCTILLION)
                    + "ни ";
        }
    }

    public static String getBetweenOctillionAndNonillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger octillionsCount;
        if (!num.mod(Constants.OCTILLION).equals(BigInteger.ZERO)) {
            octillionsCount = num.subtract(num.mod(Constants.OCTILLION));
        } else {
            octillionsCount = num.divide(Constants.OCTILLION);
        }
        String octillionsCountInLezgi = getHundredPlusNumCount(octillionsCount);
        if (octillionsCountInLezgi == null) {
            octillionsCountInLezgi = getCompound(octillionsCount);
        }
        BigInteger sum = num.add(followUpNumber);
        String octillionPlusBase = getOctillionPlusBase(sum);
        return octillionsCountInLezgi + " " + octillionPlusBase;
    }

    public static String getNonillionPlusBase(BigInteger num) {
        BigInteger hundred = Constants.NONILLION;
        if (num.mod(hundred).equals(BigInteger.ZERO)) {
            return Constants.getNameByNumber(Constants.NONILLION);
        } else {
            return Constants.getNameByNumber(Constants.NONILLION)
                    + "ни ";
        }
    }

    public static String getCompound(BigInteger num) {
        return "";
    }

    public static String getAtomicOrCompound(BigInteger num) {
        String result = Constants.getNameByNumber(num);
        return result != null ? result : getCompound(num);
    }

    public static String numToLezgi(BigInteger num) {
        if (num == null) {
            throw new IllegalArgumentException("Provided value is null");
        }
        boolean isNegative = num.signum() < 0;
        num = num.abs();
        String numberName = Constants.getNameByNumber(num);
        return isNegative ? Constants.MINUS + " " + numberName
                : numberName;
    }

    /**
     * Checks if the first BigInteger is greater than the second.
     *
     * @param num          The BigInteger to compare.
     * @param compareValue The BigInteger to compare against.
     * @return {@code true} if {@code num} is greater than {@code compareValue}; {@code false} otherwise.
     */
    public static boolean isBigIntegerGreaterThan(BigInteger num, BigInteger compareValue) {
        return num.compareTo(compareValue) > 0;
    }

    /**
     * Checks if the first BigInteger is less than the second.
     *
     * @param num          The BigInteger to compare.
     * @param compareValue The BigInteger to compare against.
     * @return {@code true} if {@code num} is less than {@code compareValue}; {@code false} otherwise.
     */
    public static boolean isBigIntegerLessThan(BigInteger num, BigInteger compareValue) {
        return num.compareTo(compareValue) < 0;
    }

    /**
     * Checks if the first BigInteger is equal to the second.
     *
     * @param num          The BigInteger to compare.
     * @param compareValue The BigInteger to compare against.
     * @return {@code true} if {@code num} is equal to {@code compareValue}; {@code false} otherwise.
     */
    public static boolean isBigIntegerEqualTo(BigInteger num, BigInteger compareValue) {
        return num.compareTo(compareValue) == 0;
    }

    /**
     * Represents a numerical range with a start and end point, both inclusive,
     * defined by {@link BigInteger} values. This class is used to define ranges of
     * numbers for specific purposes, such as categorizing numbers into different
     * magnitudes or ranges.
     */
    private static class Range {
        private final BigInteger start;
        private final BigInteger end;

        private Range(BigInteger start, BigInteger end) {
            this.start = start;
            this.end = end;
        }

        public static List<Range> ranges = List.of(
                new Range(Constants.NONILLION, Constants.OCTILLION),
                new Range(Constants.OCTILLION, Constants.SEPTILLION),
                new Range(Constants.SEPTILLION, Constants.SEXTILLION),
                new Range(Constants.SEXTILLION, Constants.QUINTILLION),
                new Range(Constants.QUADRILLION, Constants.QUINTILLION),
                new Range(Constants.TRILLION, Constants.QUADRILLION),
                new Range(Constants.BILLION, Constants.TRILLION),
                new Range(Constants.NONILLION, Constants.BILLION),
                new Range(new BigInteger("1000"), Constants.MILLION)
        );
    }
}
