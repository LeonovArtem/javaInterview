package al.spring.unit;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterizedTestExample {

    @ParameterizedTest
    @ValueSource(strings = {"hello", "world"})
    void execute(String message) {
        System.out.println(message);
    }
}
