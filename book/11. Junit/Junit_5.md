# Junit
Docs: https://junit.org/junit5/docs/current/user-guide/

* [Assertions](#1-assertions)
* [Сортировка](#2-order)

## Жизненный цикл

![test_life_cycle.jpg](img%2Ftest_life_cycle.jpg)
Example:

```java
import org.junit.jupiter.api.*;

public class FirstCycleTest {
    // Должны быть static!
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

    // Должны быть static!
    @AfterAll
    static void afterAll(){
        System.out.println("@AfterAll");
    }
}
```
Out:
```
@BeforeAll
@BeforeEach
Second test executed!
@AfterEach
@BeforeEach
First test executed!
@AfterEach
@AfterAll
```
> @AfterAll и и @BeforeAll должны быть static!
> Это связано с тем, что по умолчанию
> @TestInstance(TestInstance.Lifecycle.PER_METHOD)
> 
> Нужно поменять на @TestInstance(TestInstance.Lifecycle.PER_CLASS)

```java
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
```

1. @BeforeEach
```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FirstCycleTest {
    @BeforeEach
    void prepare(){
        System.out.println("Before each: " + this.getClass().getSimpleName());
    }

    @Test
    public void first() {
        System.out.println("First test executed!");
    }

    @Test
    public void second() {
        System.out.println("Second test executed!");
    }
}
```
Out:
```
Before each: FirstCycleTest
Second test executed!
Before each: FirstCycleTest
First test executed!
```

2. @AfterEach (допустим хотим удалить данные из базы)
```java
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class SecondCycleTest {
    @AfterEach
    void deleteDataFromDatabase(){
        System.out.println("@AfterEach: " + this.getClass().getSimpleName());
    }

    @Test
    public void first() {
        System.out.println("First test executed!");
    }

    @Test
    public void second() {
        System.out.println("Second test executed!");
    }
}
```
Out:
```
Second test executed!
@AfterEach: SecondCycleTest
First test executed!
@AfterEach: SecondCycleTest
```
3. @BeforeAll
4. @AfterAll


### 1. Assertions
```java
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

        Assertions.assertNotNull(list1);
        Assertions.assertEquals(list1, list2);
    }
}
```
### 2. Order
Сортировка методов

* @Order(1)
* @Order(2)

Без этого работать не будет:
```java
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
```
```java
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// Без этого работать не будет
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
```
