package io.lekitech;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LezgiToNumTest {
    @Test
    public void LezgiToNumExpressTest() {
        Map<String, BigInteger> map = Map.ofEntries(
                Map.entry("агъзурни кIуьд вишни кьудкъанни ругуд", BigInteger.valueOf(1986)),
                Map.entry("агъзурни кIуьд вишни цIерид", BigInteger.valueOf(1917)),
                Map.entry("агъзурни кIуьд вишни къанни цIерид", BigInteger.valueOf(1937)),
                Map.entry("кьуд миллиардни вишни цIипуд миллионни кьве " +
                                "вишни пудкъанни ирид агъзурни вад вишни яхцIурни цIерид",
                        new BigInteger("4113267557")),
                Map.entry("кьве агъзурни къанни кьуд", BigInteger.valueOf(2024)),
                Map.entry("виш агъзур", BigInteger.valueOf(100000)),
                Map.entry("кьве миллион", BigInteger.valueOf(2000000)),
                Map.entry("кьве миллионни сад", BigInteger.valueOf(2000001)),
                Map.entry("ирид виш", BigInteger.valueOf(700)),
                Map.entry("агъзурни сад", BigInteger.valueOf(1001)),
                Map.entry("вишни кьвед", BigInteger.valueOf(102)),
                Map.entry("вишни яхцIурни цIерид агъзур", BigInteger.valueOf(157000)),
                Map.entry("кIуьд квадриллионни ирид триллионни вишни кьудкъанни " +
                                "цIекIуьд миллиардни кьве вишни яхцIурни цIикьуд миллионни ирид вишни " +
                                "яхцIур агъзурни кIуьд вишни кьудкъанни цIусад",
                        new BigInteger("9007199254740991")),
                Map.entry("минус вишни кьвед", BigInteger.valueOf(-102)),
                Map.entry("минус кIуьд квадриллионни ирид триллионни вишни кьудкъанни " +
                                "цIекIуьд миллиардни кьве вишни яхцIурни цIикьуд миллионни ирид вишни " +
                                "яхцIур агъзурни кIуьд вишни кьудкъанни цIусад",
                        new BigInteger("-9007199254740991")),
                Map.entry("цIуд агъзурни вишни къанни сад", BigInteger.valueOf(10121)),
                Map.entry("кьве вишни къанни сад", BigInteger.valueOf(221))
        );
        map.forEach((k, v) -> {
            assertThat(LezgiToNum.lezgiToNum(k)).isEqualTo(v);
        });
    }
}