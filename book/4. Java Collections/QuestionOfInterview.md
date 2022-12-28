# Hash Map
https://habr.com/ru/post/162017/ - больше примеров

### 1. В каком случае может быть потерян элемент в HashMap?
```java
// В каком случае может быть потерян элемент в HashMap?
public class HashMapLoseElement {
    public static void main(String[] args) {
        var user1 = new User(1, "artem");
        var user2 = new User(2, "admin");

        // Ключ это объект!
        HashMap<User, Integer> map = new HashMap<>();

        map.put(user1, user1.getId());
        map.put(user2, user2.getId());
        
        // Меняем поле!
        user1.setName("NEW NAME");
        
        System.out.println("User_1: " + map.get(user1));
        System.out.println("User_2: " +map.get(user2));
    }
}
```
OUT:
```
// Элемент найден! Так как не участвует в equals
User_1: 1
User_2: 2
```
```java
@Getter
@Setter
@AllArgsConstructor
class User {
    private Integer id;
    private String name;
    
    // Поле 'name' не участвует
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
```
Меняем equals
```java
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
class User {
    private Integer id;
    private String name;
}
```
OUT:
```
// Элемент не найден! Так как поле 'name' - учасвует в equals
User_1: null
User_2: 2
```

### 2. Почему нельзя использовать byte[] в качестве ключа в HashMap?
```java
// Почему нельзя использовать byte[] в качестве ключа в HashMap?
public class HashMapWithByteArr {
    public static void main(String[] args) {
        /**
         * хеш-код массива не зависит от хранимых в нем элементов, а присваивается при создании массива:
         * Метод вычисления хеш-кода массива не переопределен 
         * и вычисляется по стандартному Object.hashCode() на основании адреса массива.
         */
        HashMap<byte[], Integer> map = new HashMap<>();
        byte[] arr1 = new byte[] {'a', 'b'};
        byte[] arr2 = new byte[] {'c', 'd'};
        byte[] arr3 = new byte[] {'a', 'b'};
        map.put(arr1, 1);
        map.put(arr2, 2);
        map.put(arr3, 3);

        // Все элементы добавились - хотя arr1 == arr3 !
        System.out.println(map);

        /**
         * Так же у массивов не переопределен equals и выполняет сравнение указателей.
         * Это приводит к тому, что обратиться к сохраненному с ключом-массивом элементу
         * не получится при использовании другого массива такого же размера и с такими же элементами,
         * доступ можно осуществить лишь в одном случае — при использовании той же самой ссылки на массив,
         * что использовалась для сохранения элемента.
         */
        System.out.println("Arr1: " + map.get(arr1));
        System.out.println("Arr2: " + map.get(arr2));
        System.out.println("Get by byte[]: " + map.get(new byte[] {'a', 'b'}));
    }
}
```
OUT:
```
Arr1: 1
Arr2: 2
Get by byte[]: null
```
