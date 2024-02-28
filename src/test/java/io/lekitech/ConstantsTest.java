package io.lekitech;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.*;

public class ConstantsTest {
    @Test
    public void getNameByNumberTest() {
        BigInteger bigInteger = new BigInteger("1");
        String actual = Constants.getNameByNumber(bigInteger);
        String expected = "сад";
        assertThat(actual).isEqualTo(expected);
    }
}