package io.lekitech;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static io.lekitech.Constants.*;

public class LezgiToNum {
    public static BigInteger lezgiToNum(String lezgiNum) {
        if (lezgiNum == null) {
            throw new IllegalArgumentException("Provided value is not a string");
        }
        boolean isNegative = lezgiNum.startsWith(MINUS);
        if (isNegative) {
            lezgiNum = lezgiNum.replace(MINUS, "");
        }
        String[] lezgiNumeralArray = lezgiNum.trim().split(" ");
        if (lezgiNumeralArray.length == 1) {
            if (isValidKeyByNumeral(lezgiNumeralArray[0])) {
                Numeral numeral = Constants.getNumeralByKey(lezgiNum);
                if (numeral.requiresNext) {
                    throw new IllegalArgumentException(String.format(
                            "Provided value '%s' requires a next value e.g. '%s'",
                            lezgiNum,
                            numeral.allowedNext != null ? numeral.allowedNext.minStr : "сад"
                    ));
                }
            } else {
                throw new IllegalArgumentException("Provided value is not a valid Lezgi numeral");
            }
            return isNegative ? getNumeralValueByKey(lezgiNumeralArray[0]).negate()
                    : getNumeralValueByKey(lezgiNumeralArray[0]);
        }
        List<BigInteger> mappedNumeralsChunks = new ArrayList<>();
        Numeral[] mappedNumeralArray = new Numeral[lezgiNumeralArray.length];
        for (int i = 0; i < lezgiNumeralArray.length; i++) {
            String lezgiNumeral = lezgiNumeralArray[i];
            if (!isValidKeyByNumeral(lezgiNumeral)) {
                throw new IllegalArgumentException("Provided value is not a valid Lezgi numeral: " + lezgiNumeral);
            }
            mappedNumeralArray[i] = getNumeralByKey(lezgiNumeral);
        }
        mappedNumeralsChunks.add(mappedNumeralArray[0].value);
        for (int i = 1; i < mappedNumeralArray.length; i++) {
            Numeral previous = mappedNumeralArray[i - 1];
            Numeral curr = mappedNumeralArray[i];
            if (previous.allowedNext != null
                    && (curr.value.compareTo(BigInteger.valueOf(previous.allowedNext.min)) < 0
                            || curr.value.compareTo(previous.allowedNext.max) > 0)) {
                throw new IllegalArgumentException(String.format(
                        "In the provided value '%s' should be a number between '%s' and '%s' after '%s', "
                                + "but '%s' was provided which equals to '%s'",
                        lezgiNum, previous.allowedNext.minStr, previous.allowedNext.max, lezgiNumeralArray[i - 1],
                        lezgiNumeralArray[i], curr.value));
            }
            if (curr.requiresNext && i == mappedNumeralArray.length - 1) {
                throw new IllegalArgumentException("Provided value '" + lezgiNum
                        + "' requires a next value, but none was provided");
            }
            if (previous.value.compareTo(curr.value) > 0) {
                if (mappedNumeralsChunks.get(mappedNumeralsChunks.size() - 1)
                        .compareTo(BigInteger.valueOf(1000)) < 0) {
                    mappedNumeralsChunks.set(mappedNumeralsChunks.size() - 1,
                            mappedNumeralsChunks.get(mappedNumeralsChunks.size() - 1).add(curr.value));
                } else {
                    mappedNumeralsChunks.add(curr.value);
                }
            } else if (previous.value.compareTo(curr.value) < 0) {
                mappedNumeralsChunks.set(mappedNumeralsChunks.size() - 1,
                        mappedNumeralsChunks.get(mappedNumeralsChunks.size() - 1).multiply(curr.value));
            }
        }
        BigInteger result = BigInteger.ZERO;
        for (BigInteger chunk : mappedNumeralsChunks) {
            result = result.add(chunk);
        }
        return isNegative ? result.negate() : result;
    }
}
