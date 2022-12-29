package al.spring.unit;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedMethodTest {

    @Order(2)
    @Test
    public void firstTest() {
        System.out.println("First");
    }

    @Order(1)
    @Test
    public void secondTest() {
        System.out.println("Second");
    }
}
