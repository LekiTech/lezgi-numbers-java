package temp;

import io.lekitech.LezgiNumbers;

import java.math.BigInteger;

public class TempClass {
    public static void main(String[] args) {
        System.out.println(LezgiNumbers.numToLezgiList(new BigInteger("16253")));
        System.out.println(LezgiNumbers.numToLezgi(new BigInteger("16253")));
        System.out.println(LezgiNumbers.lezgiToNum("агъзурни кIуьд вишни къанни цIерид"));
    }
}
