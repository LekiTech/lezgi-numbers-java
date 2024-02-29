package io.lekitech;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NumToLezgiTest {
    @Test
    public void testNumToLezgi() {
        BigInteger bigInteger = new BigInteger("10");
        String actual = NumToLezgi.numToLezgi(bigInteger);
        String expected = "цIуд";
        assertThat(actual).isEqualTo(expected);
        bigInteger = new BigInteger("-10");
        actual = NumToLezgi.numToLezgi(bigInteger);
        expected = "минус цIуд";
        assertThat(actual).isEqualTo(expected);
        bigInteger = new BigInteger("-0");
        actual = NumToLezgi.numToLezgi(bigInteger);
        expected = "нул";
        assertThat(actual).isEqualTo(expected);
    }
}