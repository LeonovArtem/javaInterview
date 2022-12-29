package al.spring.unit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AssertJExample {

    @Test
    public void firstTest() {
        List<Integer> userIds = List.of(1, 2, 3);
        Assertions.assertThat(userIds)
                .hasSize(3);
    }
}
