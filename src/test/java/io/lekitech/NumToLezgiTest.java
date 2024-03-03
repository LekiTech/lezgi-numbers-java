package io.lekitech;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NumToLezgiTest {
    @Test
    public void NumToLezgiExpressTest() {
        Map<BigInteger, String> map = Map.ofEntries(
                Map.entry(BigInteger.valueOf(1986),
                        "агъзурни кIуьд вишни кьудкъанни ругуд"),
                Map.entry(BigInteger.valueOf(1917),
                        "агъзурни кIуьд вишни цIерид"),
                Map.entry(BigInteger.valueOf(1937),
                        "агъзурни кIуьд вишни къанни цIерид"),
                Map.entry(new BigInteger("4113267557"),
                        "кьуд миллиардни вишни цIипуд миллионни кьве вишни " +
                                "пудкъанни ирид агъзурни вад вишни яхцIурни цIерид"),
                Map.entry(BigInteger.valueOf(2024),
                        "кьве агъзурни къанни кьуд"),
                Map.entry(BigInteger.valueOf(100000),
                        "виш агъзур"),
                Map.entry(BigInteger.valueOf(2000000),
                        "кьве миллион"),
                Map.entry(BigInteger.valueOf(2000001),
                        "кьве миллионни сад"),
                Map.entry(BigInteger.valueOf(700),
                        "ирид виш"),
                Map.entry(BigInteger.valueOf(1001),
                        "агъзурни сад"),
                Map.entry(BigInteger.valueOf(102),
                        "вишни кьвед"),
                Map.entry(BigInteger.valueOf(-102),
                        "минус вишни кьвед"),
                Map.entry(new BigInteger("9007199254740991"),
                        "кIуьд квадриллионни ирид триллионни вишни кьудкъанни цIекIуьд "
                                + "миллиардни кьве вишни яхцIурни цIикьуд миллионни ирид вишни яхцIур "
                                + "агъзурни кIуьд вишни кьудкъанни цIусад"),
                Map.entry(new BigInteger("9007199254720991"),
                        "кIуьд квадриллионни ирид триллионни вишни кьудкъанни цIекIуьд "
                                + "миллиардни кьве вишни яхцIурни цIикьуд миллионни ирид вишни къад "
                                + "агъзурни кIуьд вишни кьудкъанни цIусад")
        );
        map.forEach((k, v) -> {
            assertThat(NumToLezgi.numToLezgi(k)).isEqualTo(v);
        });
    }
}