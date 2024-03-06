package io.lekitech;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        BigInteger bigInteger = new BigInteger("2000001");
        System.out.println(NumToLezgi.numToLezgi(bigInteger));
    }
}
