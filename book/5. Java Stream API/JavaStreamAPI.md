https://quizlet.com/ru/603686589/javamentor-27-flash-cards/

# **Java stream API**

* Семейства функциональных интерфейсов
* Какие бывают стримы?
* Промежуточные и терминальные методы.
* В чем разница map и flatMap?
* Способы получения стрима (!).
* Для чего нужны методы forEach() и forEachOrdered()
* Любой анонимный класс можно заменить на лямбду?
* Что такое ленивая инициализация стрима?
* В чем разница методов range и rangeClosed?
* Что такое method reference

## Семейства функциональных интерфейсов
```Predicate<T>``` - проверяет соблюдение некоторого условия. Возвращает true или false.
```java
public static void main(String[] args) {
    Stream
            .iterate(1, n -> n + 1)
            .limit(10)
            .filter(new Predicate<Integer>() {
                @Override
                public boolean test(Integer n) {
                    return n % 2 == 0;
                }
            })
            .forEach(System.out::println);
}
```
Output:
```
2 4 6 8 10
```

```BinaryOperator<T>``` принимает в качестве параметра два объекта типа T, 
выполняет над ними бинарную операцию и возвращает ее результат также в виде объекта типа T.

```java
public static void main(String[] args) {
    Optional<Integer> sum = Stream
            .iterate(1, n -> n + 1)
            .limit(5)
            .reduce(new BinaryOperator<Integer>() {
                @Override
                public Integer apply(Integer integer, Integer integer2) {
                    return integer + integer2;
                }
            });

    sum.ifPresent(System.out::println);
}
```
Output:
```
15
```

```UnaryOperator``` - аналогичен BinaryOperator, но принимает в качестве аргумента 
только один параметр.
```java
public static void main(String[] args) {
    Stream
            .iterate(1, new UnaryOperator<Integer>() {
                @Override
                public Integer apply(Integer integer) {
                    return integer + 1;
                }
            })
            .limit(5)
            .forEach(System.out::println);

}
```

Output:
```
1 2 3 4 4
```

```Function<T,R>``` - представляет функцию перехода от объекта типа T к объекту типа R.
```java
public static void main(String[] args) {
    Stream
            .iterate(1, integer -> integer + 1)
            .limit(5)
            .map(new Function<Integer, String>() {
                @Override
                public String apply(Integer integer) {
                    return "|" + integer + "|";
                }
            })
            .forEach(System.out::println);

}
```
Output:
```
|1| |2| |3| |4| |5|
```

```Consumer<T>``` выполняет некоторое действие над объектом типа T, при этом ничего 
не возвращая.
```java
public static void main(String[] args) {
    Stream
            .iterate(1, integer -> integer + 1)
            .limit(5)
            .forEach(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) {
                    System.out.println(integer);
                }
            });

}
```
Output:
```
1 2 3 4 5
```

```Supplier<T>``` - не принимает никаких аргументов, но должен возвращать объект типа T.

```java
public static void main(String[] args) {
    Random random = new Random();

    Stream
            .generate(new Supplier<Integer>() {
                @Override
                public Integer get() {
                    return random.nextInt(10);
                }
            })
            .limit(5)
            .forEach(System.out::println);

}
```
Output:
```
randomInt randomInt randomInt randomInt randomInt 
```

## Какие бывают стримы?
1. Конечные и бесконечные.
2. Последовательные и параллельные.
3. Примитивные и объектные.

## Промежуточные и терминальные методы.
**Промежуточные** ("intermediate", ещё называют "lazy") - обрабатывают поступающие 
элементы и возвращают стрим.

**Терминальные** ("terminal", ещё называют "eager") — обрабатывают элементы 
и завершают работу стрима.

## В чем разница map и flatMap?
```.map()``` - преобразовывает Stream одного типа данных в Stream другого типа.

```java
public static void main(String[] args) {
    Stream
            .iterate(1, integer -> integer + 1)
            .limit(5)
            .map(integer -> integer * 100)
            .forEach(System.out::println);
}
```
Output:
```
100 200 300 400 500
```

```.flatMap()``` - "разворачивает" Stream-ы в один.
```java
public static void main(String[] args) {
    Integer[][] numbers = new Integer[][]{{1, 2}, {3, 4}};

    Arrays
            .stream(numbers)
            .flatMap((Function<Integer[], Stream<?>>) Arrays::stream)
            .forEach(System.out::println);
}
```
Output:
```
1 2 3 4
```

## Для чего нужны методы forEach() и forEachOrdered()