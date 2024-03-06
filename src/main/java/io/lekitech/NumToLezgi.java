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

    public static String getTenPlusBase(BigInteger num) {
        if (isLessThan(num, BigInteger.TEN)
                || isGreaterThan(num, BigInteger.valueOf(20))
                || isEqualTo(num, BigInteger.valueOf(20))) {
            throw new IllegalArgumentException("Invalid number");
        }
        String result10 = getName(BigInteger.valueOf(10));
        if (isEqualTo(num, BigInteger.TEN)) {
            resultList.add(result10);
            return result10;
        }
        String base10 = result10.
                substring(0, result10.length() - 2);
        if (isEqualTo(num, BigInteger.valueOf(11))
                || isEqualTo(num, BigInteger.valueOf(15))
                || isEqualTo(num, BigInteger.valueOf(16))) {
            resultList.add(base10 + "у");
            return base10 + "у";
        } else if (isLessThan(num, BigInteger.valueOf(15))) {
            resultList.add(base10 + "и");
            return base10 + "и";
        }
        resultList.add(base10 + "е");
        return base10 + "е";
    }

    public static String getTwentyPlusBase(BigInteger num) {
        resultList.add(isEqualTo(num, BigInteger.valueOf(20))
                    ? getName(BigInteger.valueOf(20)) : "къанни");
        //resultList.add(" ");
        return isEqualTo(num, BigInteger.valueOf(20))
                ? getName(BigInteger.valueOf(20)) : "къанни ";
    }

    public static String getThirtyPlusBase(BigInteger num) {
        return getTwentyPlusBase(num)
                + getTenPlusBase(num.subtract(BigInteger.valueOf(20)));
    }

    public static String getFourtyPlusBase(BigInteger num) {
        resultList.add(getName(BigInteger.valueOf(40)));
        if (!isEqualTo(num, BigInteger.valueOf(40))) {
            resultList.add("ни");
            //resultList.add(" ");
        }
        return isEqualTo(num, BigInteger.valueOf(40))
                ? getName(BigInteger.valueOf(40))
                : getName(BigInteger.valueOf(40)) + "ни ";
    }

    public static String getFiftyPlusBase(BigInteger num) {
        return getFourtyPlusBase(num)
                + getTenPlusBase(num.subtract(BigInteger.valueOf(40)));
    }

    public static String getSixtyPlusBase(BigInteger num) {
        if (isEqualTo(num, BigInteger.valueOf(60))) {
            resultList.add(getName(BigInteger.valueOf(3)));
            resultList.add(getName(BigInteger.valueOf(20)));
        } else {
            resultList.add(getName(BigInteger.valueOf(3)));
        }
        return isEqualTo(num, BigInteger.valueOf(60))
                ? getName(BigInteger.valueOf(3)) + getName(BigInteger.valueOf(20))
                : getName(BigInteger.valueOf(3)) + getTwentyPlusBase(num);
    }

    public static String getSeventyPlusBase(BigInteger num) {
        return getSixtyPlusBase(BigInteger.valueOf(61))
                + getTenPlusBase(num.subtract(BigInteger.valueOf(60)));
    }

    public static String getEightyPlusBase(BigInteger num) {
        if (isEqualTo(num, BigInteger.valueOf(80))) {
            resultList.add(getName(BigInteger.valueOf(4)));
            resultList.add(getName(BigInteger.valueOf(20)));
        } else {
            resultList.add(getName(BigInteger.valueOf(4)));
        }
        return isEqualTo(num, BigInteger.valueOf(80))
                ? getName(BigInteger.valueOf(4)) + getName(BigInteger.valueOf(20))
                : getName(BigInteger.valueOf(4)) + getTwentyPlusBase(num);
    }

    public static String getNinetyPlusBase(BigInteger num) {
        return getEightyPlusBase(BigInteger.valueOf(81))
                + getTenPlusBase(num.subtract(BigInteger.valueOf(80)));
    }

    public static String getHundredPlusBase(BigInteger num) {
        resultList.add(getName(BigInteger.valueOf(100)));
        if (!isEqualTo(num.mod(BigInteger.valueOf(100)), BigInteger.ZERO)) {
            resultList.add("ни");
            resultList.add(" ");
        }
        return isEqualTo(num.mod(BigInteger.valueOf(100)), BigInteger.ZERO)
                ? getName(BigInteger.valueOf(100)) : getName(BigInteger.valueOf(100)) + "ни ";
    }

    public static String getHundredPlusNumCount(BigInteger num) {
        resultList.add(isEqualTo(num, BigInteger.valueOf(2))
                    ? getName(num).substring(0, getName(num).length() - 1) : getName(num));
        return isEqualTo(num, BigInteger.valueOf(2))
                ? getName(num).substring(0, getName(num).length() - 1) : getName(num);
    }

    public static String getBetweenHundredAndThousand(BigInteger num, BigInteger followUpNumber) {
        BigInteger hundredsCount;
        hundredsCount = !isEqualTo(num.mod(BigInteger.valueOf(100)), BigInteger.ZERO)
                ? num.subtract(num.mod(BigInteger.valueOf(100))) : num.divide(BigInteger.valueOf(100));
        return getHundredPlusNumCount(hundredsCount) + " "
                + getHundredPlusBase(num.add(followUpNumber));
    }

    public static String getThousandPlusBase(BigInteger num) {
        resultList.add(getName(BigInteger.valueOf(1000)));
        if (!isEqualTo(num.mod(BigInteger.valueOf(1000)), BigInteger.ZERO)) {
            resultList.add("ни");
            resultList.add(" ");
        }
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
        resultList.add(getName(MILLION));
        if (!isEqualTo(num.mod(MILLION), BigInteger.ZERO)) {
            resultList.add("ни");
            resultList.add(" ");
        }
        return isEqualTo(num.mod(MILLION), BigInteger.ZERO)
                ? getName(MILLION) : getName(MILLION) + "ни ";
    }

    public static String getBetweenMillionAndBillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger millionsCount;
        millionsCount = !isEqualTo(num.mod(MILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(MILLION)) : num.divide(MILLION);
        String millionsCountLezgi = getHundredPlusNumCount(millionsCount);
        if (millionsCountLezgi == null) {
            millionsCountLezgi = getCompound(millionsCount);
        }
        return millionsCountLezgi + " " + getMillionPlusBase(num.add(followUpNumber));
    }

    public static String getBillionPlusBase(BigInteger num) {
        resultList.add(getName(BILLION));
        if (!isEqualTo(num.mod(BILLION), BigInteger.ZERO)) {
            resultList.add("ни");
            resultList.add(" ");
        }
        return isEqualTo(num.mod(BILLION), BigInteger.ZERO)
                ? getName(BILLION) : getName(BILLION) + "ни ";
    }

    public static String getBetweenBillionAndTrillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger billionsCount;
        billionsCount = !isEqualTo(num.mod(BILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(BILLION)) : num.divide(BILLION);
        String billionsCountLezgi = getHundredPlusNumCount(billionsCount);
        if (billionsCountLezgi == null) {
            billionsCountLezgi = getCompound(billionsCount);
        }
        return billionsCountLezgi + " " + getBillionPlusBase(num.add(followUpNumber));
    }

    public static String getTrillionPlusBase(BigInteger num) {
        resultList.add(getName(TRILLION));
        if (!isEqualTo(num.mod(TRILLION), BigInteger.ZERO)) {
            resultList.add("ни");
            resultList.add(" ");
        }
        return isEqualTo(num.mod(TRILLION), BigInteger.ZERO)
                ? getName(TRILLION) : getName(TRILLION) + "ни ";
    }

    public static String getBetweenTrillionAndQuadrillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger trillionsCount;
        trillionsCount = !isEqualTo(num.mod(TRILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(TRILLION)) : num.divide(TRILLION);
        String trillionsCountLezgi = getHundredPlusNumCount(trillionsCount);
        if (trillionsCountLezgi == null) {
            trillionsCountLezgi = getCompound(trillionsCount);
        }
        return trillionsCountLezgi + " " + getTrillionPlusBase(num.add(followUpNumber));
    }

    public static String getQuadrillionPlusBase(BigInteger num) {
        resultList.add(getName(QUADRILLION));
        if (!isEqualTo(num.mod(QUADRILLION), BigInteger.ZERO)) {
            resultList.add("ни");
            resultList.add(" ");
        }
        return isEqualTo(num.mod(QUADRILLION), BigInteger.ZERO)
                ? getName(QUADRILLION) : getName(QUADRILLION) + "ни ";
    }

    public static String getBetweenQuadrillionAndQuintillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger quadrillionsCount;
        quadrillionsCount = !isEqualTo(num.mod(QUADRILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(QUADRILLION)) : num.divide(QUADRILLION);
        String quadrillionsCountLezgi = getHundredPlusNumCount(quadrillionsCount);
        if (quadrillionsCountLezgi == null) {
            quadrillionsCountLezgi = getCompound(quadrillionsCount);
        }
        return quadrillionsCountLezgi + " " + getQuadrillionPlusBase(num.add(followUpNumber));
    }

    public static String getQuintillionPlusBase(BigInteger num) {
        resultList.add(getName(QUINTILLION));
        if (!isEqualTo(num.mod(QUINTILLION), BigInteger.ZERO)) {
            resultList.add("ни");
            resultList.add(" ");
        }
        return isEqualTo(num.mod(QUINTILLION), BigInteger.ZERO)
                ? getName(QUINTILLION) : getName(QUINTILLION) + "ни ";
    }

    public static String getBetweenQuintillionAndSextillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger quintillionsCount;
        quintillionsCount = !isEqualTo(num.mod(QUINTILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(QUINTILLION)) : num.divide(QUINTILLION);
        String quintillionsCountLezgi = getHundredPlusNumCount(quintillionsCount);
        if (quintillionsCountLezgi == null) {
            quintillionsCountLezgi = getCompound(quintillionsCount);
        }
        return quintillionsCountLezgi + " " + getQuintillionPlusBase(num.add(followUpNumber));
    }

    public static String getSextillionPlusBase(BigInteger num) {
        resultList.add(getName(SEXTILLION));
        if (!isEqualTo(num.mod(SEXTILLION), BigInteger.ZERO)) {
            resultList.add("ни");
            resultList.add(" ");
        }
        return isEqualTo(num.mod(SEXTILLION), BigInteger.ZERO)
                ? getName(SEXTILLION) : getName(SEXTILLION) + "ни ";
    }

    public static String getBetweenSextillionAndSeptillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger sextillionsCount;
        sextillionsCount = !isEqualTo(num.mod(SEXTILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(SEXTILLION)) : num.divide(SEXTILLION);
        String sextillionsCountLezgi = getHundredPlusNumCount(sextillionsCount);
        if (sextillionsCountLezgi == null) {
            sextillionsCountLezgi = getCompound(sextillionsCount);
        }
        return sextillionsCountLezgi + " " + getSextillionPlusBase(num.add(followUpNumber));
    }

    public static String getSeptillionPlusBase(BigInteger num) {
        resultList.add(getName(SEPTILLION));
        if (!isEqualTo(num.mod(SEPTILLION), BigInteger.ZERO)) {
            resultList.add("ни");
            resultList.add(" ");
        }
        return isEqualTo(num.mod(SEPTILLION), BigInteger.ZERO)
                ? getName(SEPTILLION) : getName(SEPTILLION) + "ни ";
    }

    public static String getBetweenSeptillionAndOctillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger septillionsCount;
        septillionsCount = !isEqualTo(num.mod(SEPTILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(SEPTILLION)) : num.divide(SEPTILLION);
        String septillionsCountLezgi = getHundredPlusNumCount(septillionsCount);
        if (septillionsCountLezgi == null) {
            septillionsCountLezgi = getCompound(septillionsCount);
        }
        return septillionsCountLezgi + " " + getSeptillionPlusBase(num.add(followUpNumber));
    }

    public static String getOctillionPlusBase(BigInteger num) {
        resultList.add(getName(OCTILLION));
        if (!isEqualTo(num.mod(OCTILLION), BigInteger.ZERO)) {
            resultList.add("ни");
            resultList.add(" ");
        }
        return isEqualTo(num.mod(OCTILLION), BigInteger.ZERO)
                ? getName(OCTILLION) : getName(OCTILLION) + "ни ";
    }

    public static String getBetweenOctillionAndNonillion(BigInteger num, BigInteger followUpNumber) {
        BigInteger octillionsCount;
        octillionsCount = !isEqualTo(num.mod(OCTILLION), BigInteger.ZERO)
                ? num.subtract(num.mod(OCTILLION)) : num.divide(OCTILLION);
        String octillionsCountLezgi = getHundredPlusNumCount(octillionsCount);
        if (octillionsCountLezgi == null) {
            octillionsCountLezgi = getCompound(octillionsCount);
        }
        return octillionsCountLezgi + " " + getOctillionPlusBase(num.add(followUpNumber));
    }

    public static String getNonillionPlusBase(BigInteger num) {
        resultList.add(getName(NONILLION));
        if (!isEqualTo(num.mod(NONILLION), BigInteger.ZERO)) {
            resultList.add("ни");
            resultList.add(" ");
        }
        return isEqualTo(num.mod(NONILLION), BigInteger.ZERO)
                ? getName(NONILLION) : getName(NONILLION) + "ни ";
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
                resultList.add(getName(BigInteger.valueOf(7)).substring(1));
                System.out.println("1");
            } else if (isEqualTo(unit, BigInteger.TEN)) {
                result.add(getTenPlusBase(unit.add(followUpNumber)));
                System.out.println("2");
            } else if (isEqualTo(unit, BigInteger.valueOf(20))) {
                result.add(getTwentyPlusBase(unit.add(followUpNumber)));
                System.out.println("3");
            } else if (isEqualTo(unit, BigInteger.valueOf(30))) {
                result.add(getThirtyPlusBase(unit.add(followUpNumber)));
                System.out.println("4");
            } else if (isEqualTo(unit, BigInteger.valueOf(40))) {
                result.add(getFourtyPlusBase(unit.add(followUpNumber)));
                System.out.println("5");
            } else if (isEqualTo(unit, BigInteger.valueOf(50))) {
                result.add(getFiftyPlusBase(unit.add(followUpNumber)));
                System.out.println("6");
            } else if (isEqualTo(unit, BigInteger.valueOf(60))) {
                result.add(getSixtyPlusBase(unit.add(followUpNumber)));
                System.out.println("7");
            } else if (isEqualTo(unit, BigInteger.valueOf(70))) {
                result.add(getSeventyPlusBase(unit.add(followUpNumber)));
                System.out.println("8");
            } else if (isEqualTo(unit, BigInteger.valueOf(80))) {
                result.add(getEightyPlusBase(unit.add(followUpNumber)));
                System.out.println("9");
            } else if (isEqualTo(unit, BigInteger.valueOf(90))) {
                result.add(getNinetyPlusBase(unit.add(followUpNumber)));
                System.out.println("10");
            } else if (isEqualTo(unit, BigInteger.valueOf(100))) {
                result.add(getHundredPlusBase(unit.add(followUpNumber)));
                System.out.println("11");
            } else if (isGreaterThan(unit, BigInteger.valueOf(100)) && isLessThan(unit, BigInteger.valueOf(1000))) {
                result.add(getBetweenHundredAndThousand(unit, followUpNumber));
                System.out.println("12");
            } else if (isEqualTo(unit, BigInteger.valueOf(1000))) {
                result.add(getThousandPlusBase(unit.add(followUpNumber)));
                System.out.println("13");
            } else if (isGreaterThan(unit, BigInteger.valueOf(1000)) && isLessThan(unit, MILLION)) {
                result.add(getBetweenThousandAndMillion(unit, followUpNumber));
                System.out.println("14");
            } else if (isEqualTo(unit, MILLION)) {
                result.add(getMillionPlusBase(unit.add(followUpNumber)));
                System.out.println("15");
            } else if (isGreaterThan(unit, MILLION) && isLessThan(unit, BILLION)) {
                result.add(getBetweenMillionAndBillion(unit, followUpNumber));
                System.out.println("16");
            } else if (isEqualTo(unit, BILLION)) {
                result.add(getBillionPlusBase(unit.add(followUpNumber)));
                System.out.println("17");
            } else if (isGreaterThan(unit, BILLION) && isLessThan(unit, TRILLION)) {
                result.add(getBetweenBillionAndTrillion(unit, followUpNumber));
                System.out.println("18");
            } else if (isEqualTo(unit, TRILLION)) {
                result.add(getTrillionPlusBase(unit.add(followUpNumber)));
                System.out.println("19");
            } else if (isGreaterThan(unit, TRILLION) && isLessThan(unit, QUADRILLION)) {
                result.add(getBetweenTrillionAndQuadrillion(unit, followUpNumber));
                System.out.println("20");
            } else if (isEqualTo(unit, QUADRILLION)) {
                result.add(getQuadrillionPlusBase(unit.add(followUpNumber)));
                System.out.println("21");
            } else if (unit.compareTo(QUADRILLION) > 0 && isLessThan(unit, QUINTILLION)) {
                result.add(getBetweenQuadrillionAndQuintillion(unit, followUpNumber));
                System.out.println("22");
            } else if (isEqualTo(unit, QUINTILLION)) {
                result.add(getQuintillionPlusBase(unit.add(followUpNumber)));
                System.out.println("23");
            } else if (isGreaterThan(unit, QUINTILLION) && isLessThan(unit, SEXTILLION)) {
                result.add(getBetweenQuintillionAndSextillion(unit, followUpNumber));
                System.out.println("24");
            } else if (isEqualTo(unit, SEXTILLION)) {
                result.add(getSextillionPlusBase(unit.add(followUpNumber)));
                System.out.println("25");
            } else if (isGreaterThan(unit, SEXTILLION) && isLessThan(unit, SEPTILLION)) {
                result.add(getBetweenSextillionAndSeptillion(unit, followUpNumber));
                System.out.println("26");
            } else if (isEqualTo(unit, SEPTILLION)) {
                result.add(getSeptillionPlusBase(unit.add(followUpNumber)));
                System.out.println("27");
            } else if (isGreaterThan(unit, SEPTILLION) && isLessThan(unit, OCTILLION)) {
                result.add(getBetweenSeptillionAndOctillion(unit, followUpNumber));
                System.out.println("28");
            } else if (isEqualTo(unit, OCTILLION)) {
                result.add(getOctillionPlusBase(unit.add(followUpNumber)));
                System.out.println("29");
            } else if (isGreaterThan(unit, OCTILLION) && isLessThan(unit, NONILLION)) {
                result.add(getBetweenOctillionAndNonillion(unit, followUpNumber));
                System.out.println("30");
            } else if (isEqualTo(unit, NONILLION)) {
                result.add(getNonillionPlusBase(unit.add(followUpNumber)));
                System.out.println("31");
            } else {
                if (!(units.size() > 1 && isEqualTo(unit, BigInteger.ZERO))) {
                    result.add(getName(unit) + " ");
                    resultList.add(getName(unit) + " ");
                    System.out.println("32");
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
        if (getName(num) != null) {
            resultList.add(getName(num));
        }
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
        return isNegative ? MINUS + " " + numberName
                : numberName;
    }

    public static List<String> numToLezgiList(BigInteger num) {
        resultList.removeIf(Objects::isNull);
        return resultList;
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