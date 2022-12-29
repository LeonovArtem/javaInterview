package al.spring.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FirstExample {

    @Test
    void oneTest() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(1);
        list2.add(1);

        var isBoolean = true;

        Assertions.assertNotNull(list1);
        Assertions.assertEquals(list1, list2);
        Assertions.assertTrue(isBoolean);
    }
}
