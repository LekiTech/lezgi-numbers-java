package io.lekitech;

import java.math.BigInteger;

public class App {
    public static void main(String[] args) {
        BigInteger bigInteger1 = new BigInteger("6");
        BigInteger bigInteger2 = new BigInteger("4");
        BigInteger result = bigInteger1.mod(bigInteger2);
        System.out.println(result);
    }
}
