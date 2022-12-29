## AssertJ

[pom.xml](..%2F..%2Fspring%2Fpom.xml)
```xml
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
        </dependency>
```
1. Простой пример
```java
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
```
