package io.lekitech;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NumToLezgi {
    /**
     * Splits a BigInteger number into individual units and groups them based on predefined ranges.
     *
     * @param num The number to split into units.
     * @return A List of BigInteger representing the grouped units.
     */
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

    /**
     * Groups a list of numbers into predefined ranges and sums them up.
     *
     * @param arr    A list of numbers to group and sum.
     * @param ranges A list of Range objects defining the numerical boundaries of each group.
     * @return A List of summed values for each range, followed by any numbers not fitting into any range.
     */
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

    /**
     * Returns a string representation of a given BigInteger number, following specific rules.
     *
     * @param num The BigInteger number to process.
     * @return A string representation of the input number according to predefined rules.
     * @throws IllegalArgumentException If the input number is outside the valid range.
     */
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
     * Returns the name of an atomic or compound element based on the given number.
     *
     * @param number The BigInteger representing the atomic or compound element.
     * @return The name of the atomic or compound element, or a compound name if not found.
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
        boolean isNegative = number.signum() < 0;
        number = number.abs();
        String numberName = Constants.getNameByNumber(number);
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
