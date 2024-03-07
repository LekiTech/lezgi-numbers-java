package io.lekitech;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NumToLezgiTest {
    @Test
    public void NumToLezgiExpressTest() {
        Map<BigInteger, TestStructure> map = new HashMap<>();
        map.put(BigInteger.valueOf(1986), new TestStructure(
                "агъзурни кIуьд вишни кьудкъанни ругуд",
                List.of(
                        "агъзур", "ни", " ", "кIуьд", " ", "виш",
                        "ни", " ", "кьуд", "къанни", " ", "ругуд"
                )));
        map.put(BigInteger.valueOf(1917), new TestStructure(
                "агъзурни кIуьд вишни цIерид",
                List.of(
                        "агъзур", "ни", " ", "кIуьд", " ", "виш", "ни", " ", "цIе", "рид"
                )));
        map.put(BigInteger.valueOf(1937), new TestStructure(
                "агъзурни кIуьд вишни къанни цIерид",
                List.of(
                        "агъзур", "ни", " ", "кIуьд", " ", "виш", "ни", " ",
                        "къанни", " ", "цIе", "рид"
                )));
        map.put(new BigInteger("4113267557"), new TestStructure(
                "кьуд миллиардни вишни цIипуд миллионни кьве вишни " +
                        "пудкъанни ирид агъзурни вад вишни яхцIурни цIерид",
                List.of(
                        "кьуд", " ", "миллиард", "ни", " ", "виш", "ни", " ", "цIи", "пуд", " ",
                        "миллион", "ни", " ", "кьве", " ", "виш", "ни", " ", "пуд", "къанни",
                        " ", "ирид", " ", "агъзур", "ни", " ", "вад", " ", "виш", "ни", " ",
                        "яхцIур", "ни", " ", "цIе", "рид"
                )));
        map.put(BigInteger.valueOf(2024), new TestStructure(
                "кьве агъзурни къанни кьуд",
                List.of(
                        "кьве", " ", "агъзур", "ни", " ", "къанни", " ", "кьуд"
                )));
        map.put(BigInteger.valueOf(100000), new TestStructure(
                "виш агъзур",
                List.of(
                        "виш", " ", "агъзур"
                )));
        map.put(BigInteger.valueOf(2000000), new TestStructure(
                "кьве миллион",
                List.of(
                        "кьве", " ", "миллион"
                )));
        map.put(BigInteger.valueOf(2000001), new TestStructure(
                "кьве миллионни сад",
                List.of(
                        "кьве", " ", "миллион", "ни", " ", "сад"
                )));
        map.put(BigInteger.valueOf(700), new TestStructure(
                "ирид виш",
                List.of(
                        "ирид", " ", "виш"
                )));
        map.put(BigInteger.valueOf(1001), new TestStructure(
                "агъзурни сад",
                List.of(
                        "агъзур", "ни", " ", "сад"
                )));
        map.put(BigInteger.valueOf(102), new TestStructure(
                "вишни кьвед",
                List.of(
                        "виш", "ни", " ", "кьвед"
                )));
        map.put(BigInteger.valueOf(-102), new TestStructure(
                "минус вишни кьвед",
                List.of(
                        "минус", " ", "виш", "ни", " ", "кьвед"
                )));
        map.put(new BigInteger("9007199254740991"), new TestStructure(
                "кIуьд квадриллионни ирид триллионни вишни кьудкъанни " +
                        "цIекIуьд миллиардни кьве вишни яхцIурни цIикьуд миллионни " +
                        "ирид вишни яхцIур агъзурни кIуьд вишни кьудкъанни цIусад",
                List.of(
                        "кIуьд", " ", "квадриллион", "ни", " ", "ирид", " ", "триллион", "ни",
                        " ", "виш", "ни", " ", "кьуд", "къанни", " ", "цIе", "кIуьд", " ", "миллиард",
                        "ни", " ", "кьве", " ", "виш", "ни", " ", "яхцIур", "ни", " ", "цIи", "кьуд",
                        " ", "миллион", "ни", " ", "ирид", " ", "виш", "ни", " ", "яхцIур", " ", "агъзур",
                        "ни", " ", "кIуьд", " ", "виш", "ни", " ", "кьуд", "къанни", " ", "цIу", "сад"
                )));
        map.put(new BigInteger("9007199254720991"), new TestStructure(
                "кIуьд квадриллионни ирид триллионни вишни кьудкъанни цIекIуьд миллиардни " +
                        "кьве вишни яхцIурни цIикьуд миллионни ирид вишни къад агъзурни кIуьд " +
                        "вишни кьудкъанни цIусад",
                List.of(
                        "кIуьд", " ", "квадриллион", "ни", " ", "ирид", " ", "триллион", "ни",
                        " ", "виш", "ни", " ", "кьуд", "къанни", " ", "цIе", "кIуьд", " ", "миллиард",
                        "ни", " ", "кьве", " ", "виш", "ни", " ", "яхцIур", "ни", " ", "цIи", "кьуд",
                        " ", "миллион", "ни", " ", "ирид", " ", "виш", "ни", " ", "къад", " ", "агъзур",
                        "ни", " ", "кIуьд", " ", "виш", "ни", " ", "кьуд", "къанни", " ", "цIу", "сад"
                )));
        map.forEach((k, v) -> {
            assertThat(NumToLezgi.numToLezgi(k)).isEqualTo(v.resultString);
            assertThat(NumToLezgi.numToLezgiList(k)).isEqualTo(v.resultList);
        });
        assertThatThrownBy(() -> NumToLezgi.numToLezgi(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provided value is null");
        assertThatThrownBy(() -> NumToLezgi.numToLezgiList(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Provided value is null");
    }

    private class TestStructure {
        private final String resultString;
        private final List<String> resultList;

        public TestStructure(String resultString, List<String> resultList) {
            this.resultString = resultString;
            this.resultList = resultList;
        }
    }
}