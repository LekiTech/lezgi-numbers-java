package io.lekitech;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NumToLezgiTest {
    @Test
    public void getTenPlusBaseTest() {
        assertThatThrownBy(() -> NumToLezgi.getTenPlusBase(BigInteger.valueOf(6)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> NumToLezgi.getTenPlusBase(BigInteger.valueOf(20)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> NumToLezgi.getTenPlusBase(BigInteger.valueOf(25)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(NumToLezgi.getTenPlusBase(BigInteger.valueOf(10)))
                .isEqualTo("цIуд");
        assertThat(NumToLezgi.getTenPlusBase(BigInteger.valueOf(11)))
                .isEqualTo("цIу");
        assertThat(NumToLezgi.getTenPlusBase(BigInteger.valueOf(13)))
                .isEqualTo("цIи");
        assertThat(NumToLezgi.getTenPlusBase(BigInteger.valueOf(15)))
                .isEqualTo("цIу");
        assertThat(NumToLezgi.getTenPlusBase(BigInteger.valueOf(19)))
                .isEqualTo("цIе");
    }

    @Test
    public void numToLezgiTest() {
        assertThat(NumToLezgi.numToLezgi(BigInteger.valueOf(10)))
                .isEqualTo("цIуд");
        assertThat(NumToLezgi.numToLezgi(BigInteger.valueOf(-10)))
                .isEqualTo("минус цIуд");
        assertThat(NumToLezgi.numToLezgi(BigInteger.valueOf(-0)))
                .isEqualTo("нул");
    }
}