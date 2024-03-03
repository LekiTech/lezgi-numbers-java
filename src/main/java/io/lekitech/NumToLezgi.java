package io.lekitech;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class NumToLezgi {
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

    public static String getTenPlusBase(BigInteger num) {
        if (isLessThan(num, BigInteger.TEN)
                || isGreaterThan(num, BigInteger.valueOf(20))
                || isEqualTo(num, BigInteger.valueOf(20))) {
            throw new IllegalArgumentException("Invalid number");
        }
        String result10 = getName(BigInteger.valueOf(10));
        if (isEqualTo(num, BigInteger.TEN)) {
            return result10;
        }
        String base10 = result10.
                substring(0, result10.length() - 2);
        if (isEqualTo(num, BigInteger.valueOf(11))
                || isEqualTo(num, BigInteger.valueOf(15))
                || isEqualTo(num, BigInteger.valueOf(16))) {
            return base10 + "у";
        } else if (isLessThan(num, BigInteger.valueOf(15))) {
            return base10 + "и";
        }
        return base10 + "е";
    }

    public static String getTwentyPlusBase(BigInteger num) {
        return isEqualTo(num, BigInteger.valueOf(20))
                ? getName(BigInteger.valueOf(20)) : "къанни ";
    }

    public static String getThirtyPlusBase(BigInteger num) {
        return getTwentyPlusBase(num)
                + getTenPlusBase(num.subtract(BigInteger.valueOf(20)));
    }

    public static String getFourtyPlusBase(BigInteger num) {
        return isEqualTo(num, BigInteger.valueOf(40))
                ? getName(BigInteger.valueOf(40))
                : getName(BigInteger.valueOf(40)) + "ни ";
    }

    public static String getFiftyPlusBase(BigInteger num) {
        return getFourtyPlusBase(num)
                + getTenPlusBase(num.subtract(BigInteger.valueOf(40)));
    }

    public static String getSixtyPlusBase(BigInteger num) {
        return isEqualTo(num, BigInteger.valueOf(60))
                ? getName(BigInteger.valueOf(3)) + getName(BigInteger.valueOf(20))
                : getName(BigInteger.valueOf(3)) + getTwentyPlusBase(num);
    }

    public static String getSeventyPlusBase(BigInteger num) {
        return getSixtyPlusBase(BigInteger.valueOf(61))
                + getTenPlusBase(num.subtract(BigInteger.valueOf(60)));
    }

    public static String getEightyPlusBase(BigInteger num) {
        return isEqualTo(num, BigInteger.valueOf(80))
                ? getName(BigInteger.valueOf(4)) + getName(BigInteger.valueOf(20))
                : getName(BigInteger.valueOf(4)) + getTwentyPlusBase(num);
    }

    public static String getNinetyPlusBase(BigInteger num) {
        return getEightyPlusBase(BigInteger.valueOf(81))
                + getTenPlusBase(num.subtract(BigInteger.valueOf(80)));
    }

    public static String getHundredPlusBase(BigInteger num) {
        return isEqualTo(num.mod(BigInteger.valueOf(100)), BigInteger.ZERO)
                ? getName(BigInteger.valueOf(100)) : getName(BigInteger.valueOf(100)) + "ни ";
    }

    public static String getHundredPlusNumCount(BigInteger numCount) {
        String atomicName = getName(numCount);
        return isEqualTo(numCount, BigInteger.valueOf(2))
                ? atomicName.substring(0, atomicName.length() - 1) : atomicName;
    }

    public static String getBetweenHundredAndThousand(BigInteger num, BigInteger followUpNumber) {
        BigInteger hundredsCount;
        hundredsCount = !isEqualTo(num.mod(BigInteger.valueOf(100)), BigInteger.ZERO)
                ? num.subtract(num.mod(BigInteger.valueOf(100))) : num.divide(BigInteger.valueOf(100));
        return getHundredPlusNumCount(hundredsCount) + " "
                + getHundredPlusBase(num.add(followUpNumber));
    }

    public static String getThousandPlusBase(BigInteger num) {
        return isEqualTo(num.mod(BigInteger.valueOf(1000)), BigInteger.ZERO)
                ? getName(BigInteger.valueOf(1000)) : getName(BigInteger.valueOf(1000)) + "ни ";
    }

    public static String getBetweenThousandAndMillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger thousandsCount;
        thousandsCount = !isEqualTo(num.mod(BigInteger.valueOf(1000)), BigInteger.ZERO)
                ? num.subtract(num.mod(BigInteger.valueOf(1000))) : num.divide(BigInteger.valueOf(1000));
        String thousandsCountLezgi = getHundredPlusNumCount(thousandsCount);
        if (thousandsCountLezgi == null) {
            thousandsCountLezgi = getCompound(thousandsCount);
        }
        return thousandsCountLezgi + " " + getThousandPlusBase(num.add(followUpNumber));
    }

    public static String getMillionPlusBase(BigInteger num) {
        return isEqualTo(num.mod(Constants.MILLION), BigInteger.ZERO)
                ? getName(Constants.MILLION) : getName(Constants.MILLION) + "ни ";
    }

    public static String getBetweenMillionAndBillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger millionsCount;
        millionsCount = !isEqualTo(num.mod(Constants.MILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(Constants.MILLION)) : num.divide(Constants.MILLION);
        String millionsCountLezgi = getHundredPlusNumCount(millionsCount);
        if (millionsCountLezgi == null) {
            millionsCountLezgi = getCompound(millionsCount);
        }
        return millionsCountLezgi + " " + getMillionPlusBase(num.add(followUpNumber));
    }

    public static String getBillionPlusBase(BigInteger num) {
        return isEqualTo(num.mod(Constants.BILLION), BigInteger.ZERO)
                ? getName(Constants.BILLION) : getName(Constants.BILLION) + "ни ";
    }

    public static String getBetweenBillionAndTrillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger billionsCount;
        billionsCount = !isEqualTo(num.mod(Constants.BILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(Constants.BILLION)) : num.divide(Constants.BILLION);
        String billionsCountLezgi = getHundredPlusNumCount(billionsCount);
        if (billionsCountLezgi == null) {
            billionsCountLezgi = getCompound(billionsCount);
        }
        return billionsCountLezgi + " " + getBillionPlusBase(num.add(followUpNumber));
    }

    public static String getTrillionPlusBase(BigInteger num) {
        return isEqualTo(num.mod(Constants.TRILLION), BigInteger.ZERO)
                ? getName(Constants.TRILLION) : getName(Constants.TRILLION) + "ни ";
    }

    public static String getBetweenTrillionAndQuadrillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger trillionsCount;
        trillionsCount = !isEqualTo(num.mod(Constants.TRILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(Constants.TRILLION)) : num.divide(Constants.TRILLION);
        String trillionsCountLezgi = getHundredPlusNumCount(trillionsCount);
        if (trillionsCountLezgi == null) {
            trillionsCountLezgi = getCompound(trillionsCount);
        }
        return trillionsCountLezgi + " " + getTrillionPlusBase(num.add(followUpNumber));
    }

    public static String getQuadrillionPlusBase(BigInteger num) {
        return isEqualTo(num.mod(Constants.QUADRILLION), BigInteger.ZERO)
                ? getName(Constants.QUADRILLION) : getName(Constants.QUADRILLION) + "ни ";
    }

    public static String getBetweenQuadrillionAndQuintillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger quadrillionsCount;
        quadrillionsCount = !isEqualTo(num.mod(Constants.QUADRILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(Constants.QUADRILLION)) : num.divide(Constants.QUADRILLION);
        String quadrillionsCountLezgi = getHundredPlusNumCount(quadrillionsCount);
        if (quadrillionsCountLezgi == null) {
            quadrillionsCountLezgi = getCompound(quadrillionsCount);
        }
        return quadrillionsCountLezgi + " " + getQuadrillionPlusBase(num.add(followUpNumber));
    }

    public static String getQuintillionPlusBase(BigInteger num) {
        return isEqualTo(num.mod(Constants.QUINTILLION), BigInteger.ZERO)
                ? getName(Constants.QUINTILLION) : getName(Constants.QUINTILLION) + "ни ";
    }

    public static String getBetweenQuintillionAndSextillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger quintillionsCount;
        quintillionsCount = !isEqualTo(num.mod(Constants.QUINTILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(Constants.QUINTILLION)) : num.divide(Constants.QUINTILLION);
        String quintillionsCountLezgi = getHundredPlusNumCount(quintillionsCount);
        if (quintillionsCountLezgi == null) {
            quintillionsCountLezgi = getCompound(quintillionsCount);
        }
        return quintillionsCountLezgi + " " + getQuintillionPlusBase(num.add(followUpNumber));
    }

    public static String getSextillionPlusBase(BigInteger num) {
        return isEqualTo(num.mod(Constants.SEXTILLION), BigInteger.ZERO)
                ? getName(Constants.SEXTILLION) : getName(Constants.SEXTILLION) + "ни ";
    }

    public static String getBetweenSextillionAndSeptillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger sextillionsCount;
        sextillionsCount = !isEqualTo(num.mod(Constants.SEXTILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(Constants.SEXTILLION)) : num.divide(Constants.SEXTILLION);
        String sextillionsCountLezgi = getHundredPlusNumCount(sextillionsCount);
        if (sextillionsCountLezgi == null) {
            sextillionsCountLezgi = getCompound(sextillionsCount);
        }
        return sextillionsCountLezgi + " " + getSextillionPlusBase(num.add(followUpNumber));
    }

    public static String getSeptillionPlusBase(BigInteger num) {
        return isEqualTo(num.mod(Constants.SEPTILLION), BigInteger.ZERO)
                ? getName(Constants.SEPTILLION) : getName(Constants.SEPTILLION) + "ни ";
    }

    public static String getBetweenSeptillionAndOctillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger septillionsCount;
        septillionsCount = !isEqualTo(num.mod(Constants.SEPTILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(Constants.SEPTILLION)) : num.divide(Constants.SEPTILLION);
        String septillionsCountLezgi = getHundredPlusNumCount(septillionsCount);
        if (septillionsCountLezgi == null) {
            septillionsCountLezgi = getCompound(septillionsCount);
        }
        return septillionsCountLezgi + " " + getSeptillionPlusBase(num.add(followUpNumber));
    }

    public static String getOctillionPlusBase(BigInteger num) {
        return isEqualTo(num.mod(Constants.OCTILLION), BigInteger.ZERO)
                ? getName(Constants.OCTILLION) : getName(Constants.OCTILLION) + "ни ";
    }

    public static String getBetweenOctillionAndNonillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger octillionsCount;
        octillionsCount = !isEqualTo(num.mod(Constants.OCTILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(Constants.OCTILLION)) : num.divide(Constants.OCTILLION);
        String octillionsCountLezgi = getHundredPlusNumCount(octillionsCount);
        if (octillionsCountLezgi == null) {
            octillionsCountLezgi = getCompound(octillionsCount);
        }
        return octillionsCountLezgi + " " + getOctillionPlusBase(num.add(followUpNumber));
    }

    public static String getNonillionPlusBase(BigInteger num) {
        return isEqualTo(num.mod(Constants.NONILLION), BigInteger.ZERO)
                ? getName(Constants.NONILLION) : getName(Constants.NONILLION) + "ни ";
    }

    public static String getCompound(BigInteger num) {
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
                result.add(getTenPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(20))) {
                result.add(getTwentyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(30))) {
                result.add(getThirtyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(40))) {
                result.add(getFourtyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(50))) {
                result.add(getFiftyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(60))) {
                result.add(getSixtyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(70))) {
                result.add(getSeventyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(80))) {
                result.add(getEightyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(90))) {
                result.add(getNinetyPlusBase(unit.add(followUpNumber)));
            } else if (isEqualTo(unit, BigInteger.valueOf(100))) {
                result.add(getHundredPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, BigInteger.valueOf(100)) && isLessThan(unit, BigInteger.valueOf(1000))) {
                result.add(getBetweenHundredAndThousand(unit, followUpNumber));
            } else if (isEqualTo(unit, BigInteger.valueOf(1000))) {
                result.add(getThousandPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, BigInteger.valueOf(1000)) && isLessThan(unit, Constants.MILLION)) {
                result.add(getBetweenThousandAndMillion(unit, followUpNumber));
            } else if (isEqualTo(unit, Constants.MILLION)) {
                result.add(getMillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, Constants.MILLION) && isLessThan(unit, Constants.BILLION)) {
                result.add(getBetweenMillionAndBillion(unit, followUpNumber));
            } else if (isEqualTo(unit, Constants.BILLION)) {
                result.add(getBillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, Constants.BILLION) && isLessThan(unit, Constants.TRILLION)) {
                result.add(getBetweenBillionAndTrillion(unit, followUpNumber));
            } else if (isEqualTo(unit, Constants.TRILLION)) {
                result.add(getTrillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, Constants.TRILLION) && isLessThan(unit, Constants.QUADRILLION)) {
                result.add(getBetweenTrillionAndQuadrillion(unit, followUpNumber));
            } else if (isEqualTo(unit, Constants.QUADRILLION)) {
                result.add(getQuadrillionPlusBase(unit.add(followUpNumber)));
            } else if (unit.compareTo(Constants.QUADRILLION) > 0 && isLessThan(unit, Constants.QUINTILLION)) {
                result.add(getBetweenQuadrillionAndQuintillion(unit, followUpNumber));
            } else if (isEqualTo(unit, Constants.QUINTILLION)) {
                result.add(getQuintillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, Constants.QUINTILLION) && isLessThan(unit, Constants.SEXTILLION)) {
                result.add(getBetweenQuintillionAndSextillion(unit, followUpNumber));
            } else if (isEqualTo(unit, Constants.SEXTILLION)) {
                result.add(getSextillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, Constants.SEXTILLION) && isLessThan(unit, Constants.SEPTILLION)) {
                result.add(getBetweenSextillionAndSeptillion(unit, followUpNumber));
            } else if (isEqualTo(unit, Constants.SEPTILLION)) {
                result.add(getSeptillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, Constants.SEPTILLION) && isLessThan(unit, Constants.OCTILLION)) {
                result.add(getBetweenSeptillionAndOctillion(unit, followUpNumber));
            } else if (isEqualTo(unit, Constants.OCTILLION)) {
                result.add(getOctillionPlusBase(unit.add(followUpNumber)));
            } else if (isGreaterThan(unit, Constants.OCTILLION) && isLessThan(unit, Constants.NONILLION)) {
                result.add(getBetweenOctillionAndNonillion(unit, followUpNumber));
            } else if (isEqualTo(unit, Constants.NONILLION)) {
                result.add(getNonillionPlusBase(unit.add(followUpNumber)));
            } else {
                if (!(units.size() > 1 && isEqualTo(unit, BigInteger.ZERO))) {
                    result.add(getName(unit) + " ");
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : result) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString().replaceAll("  ", " ").trim();
    }

    public static String getAtomicOrCompound(BigInteger num) {
        return getName(num) != null ? getName(num) : getCompound(num);
    }

    /**
     * Converts a BigInteger number to its Lezgi language representation.
     *
     * @param num The BigInteger number to be converted.
     * @return The Lezgi language representation of the given number.
     * @throws IllegalArgumentException if the provided number is null.
     */
    public static String numToLezgi(BigInteger num) {
        if (num == null) {
            throw new IllegalArgumentException("Provided value is null");
        }
        boolean isNegative = num.signum() < 0;
        num = num.abs();
        String numberName = getAtomicOrCompound(num);
        return isNegative ? Constants.MINUS + " " + numberName
                : numberName;
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
        return Constants.getNameByNumber(num);
    }

    private static class Range {
        private final BigInteger start;
        private final BigInteger end;

        private Range(BigInteger start, BigInteger end) {
            this.start = start;
            this.end = end;
        }

        private static List<Range> ranges = List.of(
                new Range(Constants.NONILLION, Constants.OCTILLION),
                new Range(Constants.OCTILLION, Constants.SEPTILLION),
                new Range(Constants.SEPTILLION, Constants.SEXTILLION),
                new Range(Constants.SEXTILLION, Constants.QUINTILLION),
                new Range(Constants.QUADRILLION, Constants.QUINTILLION),
                new Range(Constants.TRILLION, Constants.QUADRILLION),
                new Range(Constants.BILLION, Constants.TRILLION),
                new Range(Constants.MILLION, Constants.BILLION),
                new Range(new BigInteger("1000"), Constants.MILLION)
        );
    }
}