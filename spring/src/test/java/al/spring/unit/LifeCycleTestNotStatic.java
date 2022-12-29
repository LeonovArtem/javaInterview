package al.spring.unit;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LifeCycleTestNotStatic {
    @BeforeAll
    void beforeAll(){
        System.out.println("@BeforeAll");
    }

    @Test
    public void first() {
        System.out.println("First test executed!");
    }

    @AfterAll
    void afterAll(){
        System.out.println("@AfterAll");
    }
}
