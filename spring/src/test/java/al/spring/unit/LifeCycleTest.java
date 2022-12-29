package al.spring.unit;

import org.junit.jupiter.api.*;

public class LifeCycleTest {
    @BeforeAll
    static void beforeAll(){
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    void before(){
        System.out.println("@BeforeEach");
    }

    @Test
    public void first() {
        System.out.println("First test executed!");
    }

    @Test
    public void second() {
        System.out.println("Second test executed!");
    }

    @AfterEach
    void after(){
        System.out.println("@AfterEach");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("@AfterAll");
    }
}
