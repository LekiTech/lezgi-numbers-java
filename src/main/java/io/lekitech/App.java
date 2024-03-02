package io.lekitech;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        BigInteger integer = new BigInteger("2000001");
        System.out.println(NumToLezgi.numToLezgi(integer));
        System.out.println(NumToLezgi.separateNumberIntoUnits(integer));
    }
}
