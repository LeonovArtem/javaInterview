package al.spring;

import al.spring.testcontainers.DatabaseContainer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@SpringBootTest
@TestPropertySource(properties = {"app.scheduling.enable=false",})
@Testcontainers
public abstract class BaseFunctionalTest {
    @Container
    public static final DatabaseContainer database = DatabaseContainer.getInstance();

}
