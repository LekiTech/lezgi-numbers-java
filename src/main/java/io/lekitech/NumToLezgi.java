package io.lekitech;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static io.lekitech.Constants.*;
public class NumToLezgi {
    private static List<String> resultList = new ArrayList<>();
    public static List<BigInteger> separateNumberIntoUnits(BigInteger num) {
        if (isEqualTo(num, BigInteger.ZERO)) {
            return List.of(BigInteger.ZERO);
        }
        List<BigInteger> result = new ArrayList<>();
        BigInteger ten = BigInteger.TEN;
        BigInteger one = BigInteger.ONE;
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
            BigInteger sum = BigInteger.ZERO;
            List<BigInteger> tempArr = new ArrayList<>();
            for (BigInteger num : arr) {
                if ((isGreaterThan(num, range.start) || isEqualTo(num, range.start))
                        && isLessThan(num, range.end)) {
                    sum = sum.add(num);
                } else {
                    tempArr.add(num);
                }
            }
            if (!isEqualTo(sum, BigInteger.ZERO)) {
                result.add(sum);
            }
            arr = tempArr;
        }
        result.addAll(arr);
        return result;
    }

    public static List<String> getTenPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        if (isLessThan(num, BigInteger.TEN)
                || isGreaterThan(num, BigInteger.valueOf(20))
                || isEqualTo(num, BigInteger.valueOf(20))) {
            throw new IllegalArgumentException("Invalid number");
        }
        String result10 = getName(BigInteger.valueOf(10));
        if (isEqualTo(num, BigInteger.TEN)) {
            result.add(result10);
            return result;
        }
        String base10 = result10.
                substring(0, result10.length() - 2);
        if (isEqualTo(num, BigInteger.valueOf(11))
                || isEqualTo(num, BigInteger.valueOf(15))
                || isEqualTo(num, BigInteger.valueOf(16))) {
            result.add(base10 + "у");
            return result;
        } else if (isLessThan(num, BigInteger.valueOf(15))) {
            result.add(base10 + "и");
            return result;
        }
        result.add(base10 + "е");
        return result;
    }

    public static List<String> getTwentyPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.add(isEqualTo(num, BigInteger.valueOf(20))
                ? getName(BigInteger.valueOf(20)) : "къанни");
        return result;
    }

    public static List<String> getThirtyPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.addAll(getTwentyPlusBase(num));
        result.addAll(getTenPlusBase(num.subtract(BigInteger.valueOf(20))));
        return result;
    }

    public static List<String> getFourtyPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.add(getName(BigInteger.valueOf(40)));
        if (!isEqualTo(num, BigInteger.valueOf(40))) {
            result.add("ни");
        }
        return result;
    }

    public static List<String> getFiftyPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.addAll(getFourtyPlusBase(num));
        result.addAll(getTenPlusBase(num.subtract(BigInteger.valueOf(40))));
        return result;
    }

    public static List<String> getSixtyPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        if (isEqualTo(num, BigInteger.valueOf(60))) {
            result.add(getName(BigInteger.valueOf(3)));
            result.add(getName(BigInteger.valueOf(20)));
        } else {
            result.add(getName(BigInteger.valueOf(3)));
            result.addAll(getTwentyPlusBase(num));
        }
        return result;
    }

    public static List<String> getSeventyPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.addAll(getSixtyPlusBase(BigInteger.valueOf(61)));
        result.addAll(getTenPlusBase(num.subtract(BigInteger.valueOf(60))));
        return result;
    }

    public static List<String> getEightyPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        if (isEqualTo(num, BigInteger.valueOf(80))) {
            result.add(getName(BigInteger.valueOf(4)));
            result.add(getName(BigInteger.valueOf(20)));
        } else {
            result.add(getName(BigInteger.valueOf(4)));
            result.addAll(getTwentyPlusBase(num));
        }
        return result;
    }

    public static List<String> getNinetyPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.addAll(getEightyPlusBase(BigInteger.valueOf(81)));
        result.addAll(getTenPlusBase(num.subtract(BigInteger.valueOf(80))));
        return result;
    }

    public static List<String> getHundredPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.add(getName(BigInteger.valueOf(100)));
        if (!isEqualTo(num.mod(BigInteger.valueOf(100)), BigInteger.ZERO)) {
            result.add("ни");
        }
        return result;
    }

    public static List<String> getHundredPlusNumCount(BigInteger num) {
        if (getName(num) == null) {
            return null;
        }
        List<String> result = new ArrayList<>();
        result.add(isEqualTo(num, BigInteger.valueOf(2))
                    ? getName(num).substring(0, getName(num).length() - 1) : getName(num));
        return result;
    }

    public static List<String> getBetweenHundredAndThousand(BigInteger num, BigInteger followUpNumber) {
        BigInteger hundredsCount;
        hundredsCount = !isEqualTo(num.mod(BigInteger.valueOf(100)), BigInteger.ZERO)
                ? num.subtract(num.mod(BigInteger.valueOf(100))) : num.divide(BigInteger.valueOf(100));
        List<String> result = new ArrayList<>();
        result.addAll(getHundredPlusNumCount(hundredsCount));
        result.add(" ");
        result.addAll(getHundredPlusBase(num.add(followUpNumber)));
        return result;
    }

    public static List<String> getThousandPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.add(getName(BigInteger.valueOf(1000)));
        if (!isEqualTo(num.mod(BigInteger.valueOf(1000)), BigInteger.ZERO)) {
            result.add("ни");
        }
        return result;
    }

    public static List<String> getBetweenThousandAndMillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger thousandsCount;
        thousandsCount = !isEqualTo(num.mod(BigInteger.valueOf(1000)), BigInteger.ZERO)
                ? num.subtract(num.mod(BigInteger.valueOf(1000))) : num.divide(BigInteger.valueOf(1000));
        List<String> thousandsCountLezgi = getHundredPlusNumCount(thousandsCount);
        if (thousandsCountLezgi == null) {
            thousandsCountLezgi = getCompound(thousandsCount);
        }
        List<String> result = new ArrayList<>();
        result.addAll(thousandsCountLezgi);
        result.add(" ");
        result.addAll(getThousandPlusBase(num.add(followUpNumber)));
        return result;
    }

    public static List<String> getMillionPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.add(getName(MILLION));
        if (!isEqualTo(num.mod(MILLION), BigInteger.ZERO)) {
            result.add("ни");
        }
        return result;
    }

    public static List<String> getBetweenMillionAndBillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger millionsCount;
        millionsCount = !isEqualTo(num.mod(MILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(MILLION)) : num.divide(MILLION);
        List<String> millionsCountLezgi = getHundredPlusNumCount(millionsCount);
        if (millionsCountLezgi == null) {
            millionsCountLezgi = getCompound(millionsCount);
        }
        List<String> result = new ArrayList<>();
        result.addAll(millionsCountLezgi);
        result.add(" ");
        result.addAll(getMillionPlusBase(num.add(followUpNumber)));
        return result;
    }

    public static List<String> getBillionPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.add(getName(BILLION));
        if (!isEqualTo(num.mod(BILLION), BigInteger.ZERO)) {
            result.add("ни");
        }
        return result;
    }

    public static List<String> getBetweenBillionAndTrillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger billionsCount;
        billionsCount = !isEqualTo(num.mod(BILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(BILLION)) : num.divide(BILLION);
        List<String> billionsCountLezgi = getHundredPlusNumCount(billionsCount);
        if (billionsCountLezgi == null) {
            billionsCountLezgi = getCompound(billionsCount);
        }
        List<String> result = new ArrayList<>();
        result.addAll(billionsCountLezgi);
        result.add(" ");
        result.addAll(getBillionPlusBase(num.add(followUpNumber)));
        return result;
    }

    public static List<String> getTrillionPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.add(getName(TRILLION));
        if (!isEqualTo(num.mod(TRILLION), BigInteger.ZERO)) {
            result.add("ни");
        }
        return result;
    }

    public static List<String> getBetweenTrillionAndQuadrillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger trillionsCount;
        trillionsCount = !isEqualTo(num.mod(TRILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(TRILLION)) : num.divide(TRILLION);
        List<String> trillionsCountLezgi = getHundredPlusNumCount(trillionsCount);
        if (trillionsCountLezgi == null) {
            trillionsCountLezgi = getCompound(trillionsCount);
        }
        List<String> result = new ArrayList<>();
        result.addAll(trillionsCountLezgi);
        result.add(" ");
        result.addAll(getTrillionPlusBase(num.add(followUpNumber)));
        return result;
    }

    public static List<String> getQuadrillionPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.add(getName(QUADRILLION));
        if (!isEqualTo(num.mod(QUADRILLION), BigInteger.ZERO)) {
            result.add("ни");
        }
        return result;
    }

    public static List<String> getBetweenQuadrillionAndQuintillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger quadrillionsCount;
        quadrillionsCount = !isEqualTo(num.mod(QUADRILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(QUADRILLION)) : num.divide(QUADRILLION);
        List<String> quadrillionsCountLezgi = getHundredPlusNumCount(quadrillionsCount);
        if (quadrillionsCountLezgi == null) {
            quadrillionsCountLezgi = getCompound(quadrillionsCount);
        }
        List<String> result = new ArrayList<>();
        result.addAll(quadrillionsCountLezgi);
        result.add(" ");
        result.addAll(getQuadrillionPlusBase(num.add(followUpNumber)));
        return result;
    }

    public static List<String> getQuintillionPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.add(getName(QUINTILLION));
        if (!isEqualTo(num.mod(QUINTILLION), BigInteger.ZERO)) {
            result.add("ни");
        }
        return result;
    }

    public static List<String> getBetweenQuintillionAndSextillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger quintillionsCount;
        quintillionsCount = !isEqualTo(num.mod(QUINTILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(QUINTILLION)) : num.divide(QUINTILLION);
        List<String> quintillionsCountLezgi = getHundredPlusNumCount(quintillionsCount);
        if (quintillionsCountLezgi == null) {
            quintillionsCountLezgi = getCompound(quintillionsCount);
        }
        List<String> result = new ArrayList<>();
        result.addAll(quintillionsCountLezgi);
        result.add(" ");
        result.addAll(getQuintillionPlusBase(num.add(followUpNumber)));
        return result;
    }

    public static List<String> getSextillionPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.add(getName(SEXTILLION));
        if (!isEqualTo(num.mod(SEXTILLION), BigInteger.ZERO)) {
            result.add("ни");
        }
        return result;
    }

    public static List<String> getBetweenSextillionAndSeptillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger sextillionsCount;
        sextillionsCount = !isEqualTo(num.mod(SEXTILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(SEXTILLION)) : num.divide(SEXTILLION);
        List<String> sextillionsCountLezgi = getHundredPlusNumCount(sextillionsCount);
        if (sextillionsCountLezgi == null) {
            sextillionsCountLezgi = getCompound(sextillionsCount);
        }
        List<String> result = new ArrayList<>();
        result.addAll(sextillionsCountLezgi);
        result.add(" ");
        result.addAll(getSextillionPlusBase(num.add(followUpNumber)));
        return result;
    }

    public static List<String> getSeptillionPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.add(getName(SEPTILLION));
        if (!isEqualTo(num.mod(SEPTILLION), BigInteger.ZERO)) {
            result.add("ни");
        }
        return result;
    }

    public static List<String> getBetweenSeptillionAndOctillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger septillionsCount;
        septillionsCount = !isEqualTo(num.mod(SEPTILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(SEPTILLION)) : num.divide(SEPTILLION);
        List<String> septillionsCountLezgi = getHundredPlusNumCount(septillionsCount);
        if (septillionsCountLezgi == null) {
            septillionsCountLezgi = getCompound(septillionsCount);
        }
        List<String> result = new ArrayList<>();
        result.addAll(septillionsCountLezgi);
        result.add(" ");
        result.addAll(getSeptillionPlusBase(num.add(followUpNumber)));
        return result;
    }

    public static List<String> getOctillionPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.add(getName(OCTILLION));
        if (!isEqualTo(num.mod(OCTILLION), BigInteger.ZERO)) {
            result.add("ни");
        }
        return result;
    }

    public static List<String> getBetweenOctillionAndNonillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger octillionsCount;
        octillionsCount = !isEqualTo(num.mod(OCTILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(OCTILLION)) : num.divide(OCTILLION);
        List<String> octillionsCountLezgi = getHundredPlusNumCount(octillionsCount);
        if (octillionsCountLezgi == null) {
            octillionsCountLezgi = getCompound(octillionsCount);
        }
        List<String> result = new ArrayList<>();
        result.addAll(octillionsCountLezgi);
        result.add(" ");
        result.addAll(getOctillionPlusBase(num.add(followUpNumber)));
        return result;
    }

    public static List<String> getNonillionPlusBase(BigInteger num) {
        List<String> result = new ArrayList<>();
        result.add(getName(NONILLION));
        if (!isEqualTo(num.mod(NONILLION), BigInteger.ZERO)) {
            result.add("ни");
        }
        return result;
    }

    public static List<String> getCompound(BigInteger num) {
        List<BigInteger> units = separateNumberIntoUnits(num);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < units.size(); i++) {
            BigInteger unit = units.get(i);
            BigInteger followUpNumber = BigInteger.ZERO;
            for (int j = i + 1; j < units.size(); j++) {
                followUpNumber = followUpNumber.add(units.get(j));
            }
            if (i > 0 && isEqualTo(unit, BigInteger.valueOf(7))
                    && (isEqualTo(units.get(i - 1), BigInteger.TEN)
                    || isEqualTo(units.get(i - 1), BigInteger.valueOf(30))
                    || isEqualTo(units.get(i - 1), BigInteger.valueOf(50))
                    || isEqualTo(units.get(i - 1), BigInteger.valueOf(70))
                    || isEqualTo(units.get(i - 1), BigInteger.valueOf(90)))) {
                result.add(getName(BigInteger.valueOf(7)).substring(1));
            } else if (isEqualTo(unit, BigInteger.TEN)) {
                result.addAll(getTenPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(20))) {
                result.addAll(getTwentyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(30))) {
                result.addAll(getThirtyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(40))) {
                result.addAll(getFourtyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(50))) {
                result.addAll(getFiftyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(60))) {
                result.addAll(getSixtyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(70))) {
                result.addAll(getSeventyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(80))) {
                result.addAll(getEightyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(90))) {
                result.addAll(getNinetyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(100))) {
                result.addAll(getHundredPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, BigInteger.valueOf(100)) && isLessThan(unit, BigInteger.valueOf(1000))) {
                result.addAll(getBetweenHundredAndThousand(unit, followUpNumber));
            } else if (isEqualTo(unit, BigInteger.valueOf(1000))) {
                result.addAll(getThousandPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, BigInteger.valueOf(1000)) && isLessThan(unit, MILLION)) {
                result.addAll(getBetweenThousandAndMillion(unit, followUpNumber));
            } else if (isEqualTo(unit, MILLION)) {
                result.addAll(getMillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, MILLION) && isLessThan(unit, BILLION)) {
                result.addAll(getBetweenMillionAndBillion(unit, followUpNumber));
            } else if (isEqualTo(unit, BILLION)) {
                result.addAll(getBillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, BILLION) && isLessThan(unit, TRILLION)) {
                result.addAll(getBetweenBillionAndTrillion(unit, followUpNumber));
            } else if (isEqualTo(unit, TRILLION)) {
                result.addAll(getTrillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, TRILLION) && isLessThan(unit, QUADRILLION)) {
                result.addAll(getBetweenTrillionAndQuadrillion(unit, followUpNumber));
            } else if (isEqualTo(unit, QUADRILLION)) {
                result.addAll(getQuadrillionPlusBase(unit.add(followUpNumber)));
            } else if (unit.compareTo(QUADRILLION) > 0 && isLessThan(unit, QUINTILLION)) {
                result.addAll(getBetweenQuadrillionAndQuintillion(unit, followUpNumber));
            } else if (isEqualTo(unit, QUINTILLION)) {
                result.addAll(getQuintillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, QUINTILLION) && isLessThan(unit, SEXTILLION)) {
                result.addAll(getBetweenQuintillionAndSextillion(unit, followUpNumber));
            } else if (isEqualTo(unit, SEXTILLION)) {
                result.addAll(getSextillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, SEXTILLION) && isLessThan(unit, SEPTILLION)) {
                result.addAll(getBetweenSextillionAndSeptillion(unit, followUpNumber));
            } else if (isEqualTo(unit, SEPTILLION)) {
                result.addAll(getSeptillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, SEPTILLION) && isLessThan(unit, OCTILLION)) {
                result.addAll(getBetweenSeptillionAndOctillion(unit, followUpNumber));
            } else if (isEqualTo(unit, OCTILLION)) {
                result.addAll(getOctillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, OCTILLION) && isLessThan(unit, NONILLION)) {
                result.addAll(getBetweenOctillionAndNonillion(unit, followUpNumber));
            } else if (isEqualTo(unit, NONILLION)) {
                result.addAll(getNonillionPlusBase(unit.add(followUpNumber)));
            } else {
                if (!(units.size() > 1 && isEqualTo(unit, BigInteger.ZERO))) {
                    result.add(getName(unit));
                }
            }
        }
        return result;
    }

    public static List<String> getAtomicOrCompound(BigInteger num) {
        List<String> result = new ArrayList<>();
        if (getName(num) != null) {
            result.add(getName(num));
        } else {
            result.addAll(getCompound(num));
        }
        return result;
    }

    /**
     * Converts a BigInteger number to its Lezgi language representation.
     *
     * @param num The BigInteger number to be converted.
     * @return The Lezgi language representation of the given number.
     * @throws IllegalArgumentException if the provided number is null.
     */
    public static List<String> numToLezgiList(BigInteger num) {
        if (num == null) {
            throw new IllegalArgumentException("Provided value is null");
        }
        boolean isNegative = num.signum() < 0;
        num = num.abs();
        List<String> list = getAtomicOrCompound(num);
        List<String> result = new ArrayList<>();
        if (isNegative) {
            result.add(MINUS);
            result.add(" ");
            for (String el : list) {
                if (el.endsWith("ни")) {
                    result.add(el);
                    result.add(" ");
                } else {
                    result.add(el);
                }
            }
        } else {
            for (String el : list) {
                if (el.endsWith("ни")) {
                    result.add(el);
                    result.add(" ");
                } else {
                    result.add(el);
                }
            }
        }
        return result;
    }

    public static String numToLezgi(BigInteger num) {
        StringBuilder sB = new StringBuilder();
        numToLezgiList(num).stream()
                .forEach(sB::append);
        return sB.toString();
    }
    public static boolean isGreaterThan(BigInteger num, BigInteger compareValue) {
        return num.compareTo(compareValue) > 0;
    }

    public static boolean isLessThan(BigInteger num, BigInteger compareValue) {
        return num.compareTo(compareValue) < 0;
    }

    public static boolean isEqualTo(BigInteger num, BigInteger compareValue) {
        return num.compareTo(compareValue) == 0;
    }

    public static String getName(BigInteger num) {
        return getAtomicValueByKey(num);
    }

    private static class Range {
        private final BigInteger start;
        private final BigInteger end;

        private Range(BigInteger start, BigInteger end) {
            this.start = start;
            this.end = end;
        }

        private static List<Range> ranges = List.of(
                new Range(NONILLION, OCTILLION),
                new Range(OCTILLION, SEPTILLION),
                new Range(SEPTILLION, SEXTILLION),
                new Range(SEXTILLION, QUINTILLION),
                new Range(QUADRILLION, QUINTILLION),
                new Range(TRILLION, QUADRILLION),
                new Range(BILLION, TRILLION),
                new Range(MILLION, BILLION),
                new Range(new BigInteger("1000"), MILLION)
        );
    }
}