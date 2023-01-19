# Concurrent Collections
- Короткий видеоурок синхронизированные коллекции: [ссылка](https://www.youtube.com/watch?v=nifOyLM4TrI&list=PLqj7-hRTFl_oDMBjI_EstsFcDAwt-Arhs&index=27)
- ConcurrentHashMap: [ссылка](https://www.youtube.com/watch?v=j4C2o_j_y6U&list=PLqj7-hRTFl_oDMBjI_EstsFcDAwt-Arhs&index=37)

## Content
- [Synchronized collections](#1-synchronized-collections)
- [ConcurrentHashMap](#2-concurrenthashmap)
- [CopyOnWriteArrayList](#3-copyonwritearraylist)
- [CopyOnWriteArraySet](#4-copyonwritearrayset)

![Sync_collections_1.png](img%2FSync_collections_1.png)

### 1. Synchronized collections
Пример1:
```java
public class SyncCollectionEx_1 {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> source = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            source.add(i);
        }

        List<Integer> target = new ArrayList<>();

        Runnable runnable = () -> {
            target.addAll(source);
        };

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        // Результат может быть 10 или 20
        System.out.println(target.size());
    }
}
```
Решение:
```
List<Integer> target = Collections.synchronizedList(new ArrayList<>());
```
`Collections.synchronized` - устанавливает блокировки для каждого методад (sets locks for each method)

Пример2:

```java
public class SyncCollectionEx_2 {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> arrList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            arrList.add(i);
        }
        // synchronizedList
        List<Integer> list = Collections.synchronizedList(arrList);

        Runnable runnable1 = () -> {
            // Когда мы используем иттератор в многопоточном приложении будет ConcurrentModificationException!
            var iterator = list.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        };

        Runnable runnable2 = () -> {
            // Тут ставится лок
            list.remove(10);
        };

        var thread1 = new Thread(runnable1);
        var thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(list);
    }
}

```
OUT:
```
Exception in thread "Thread-0" java.util.ConcurrentModificationException
```
Решение:
```java
synchronized (list) {
    var iterator = list.iterator();
    while (iterator.hasNext()) {
        System.out.println(iterator.next());
    }
}
```
[Содержание](#content)
### 2. ConcurrentHashMap

Работает более эффективно чем Synchronized collections

![ConcurrentHashMap_topic.png](img%2FConcurrentHashMap_topic.png)

![ConcurrentHashMap_not_null.png](img%2FConcurrentHashMap_not_null.png)

ConcurrentHashMap делит элементы на сегменты(по кол-ву бакетов). 
И поэтому несколько потоков могут одновременно изменять данные в нескольких сегментах (бакетах), но не в одном и том же!
Это называется **SegmentLock** или **BacketLock**.

![ConcurrentHashMap.png](img%2FConcurrentHashMap.png)

Пример:
```java
public class ConcurrentHashMapEx {
    public static void main(String[] args) throws InterruptedException {
        // Map<Integer, String> users = new ConcurrentHashMap<>();
        Map<Integer, String> users = new HashMap<>();
        users.put(1, "Zaur");
        users.put(2, "Ivan");
        users.put(3, "Sergey");
        users.put(4, "Artem");
        users.put(5, "Lana");
        System.out.println(users);

        Runnable runnable = () -> {
            var iterator = users.keySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                var key = iterator.next();
                System.out.println(key + ":" + users.get(key));
            }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            users.put(6, "New User");
        };

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(users);
    }
}
```
OUT:
```
{1=Zaur, 2=Ivan, 3=Sergey, 4=Artem, 5=Lana}
1:Zaur
2:Ivan
Exception in thread "Thread-0" java.util.ConcurrentModificationException
	at java.base/java.util.HashMap$HashIterator.nextNode(HashMap.java:1597)
	at java.base/java.util.HashMap$KeyIterator.next(HashMap.java:1620)
	at org.aleonov.javainteview.multithreiding.collection.ConcurrentHashMapEx1.lambda$main$0(ConcurrentHashMapEx.java:25)
	at java.base/java.lang.Thread.run(Thread.java:833)
{1=Zaur, 2=Ivan, 3=Sergey, 4=Artem, 5=Lana, 6=New User}
```
Решение
```java
Map<Integer, String> users = new ConcurrentHashMap<>();
```

Пример с null:
```java
public class ConcurrentHashMapEx2 {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> users = new ConcurrentHashMap<>();
        users.put(1, "Zaur");
        users.put(null, "New user");
        users.put(2, null);
        System.out.println(users);
    }
}
```

OUT:

```
Exception in thread "main" java.lang.NullPointerException
	at java.base/java.util.concurrent.ConcurrentHashMap.putVal(ConcurrentHashMap.java:1011)
	at java.base/java.util.concurrent.ConcurrentHashMap.put(ConcurrentHashMap.java:1006)
	at org.aleonov.javainteview.multithreiding.collection.ConcurrentHashMapEx2.main(ConcurrentHashMapEx2.java:11)
```
[Содержание](#content)
### 3. CopyOnWriteArrayList

![CopyOnWrite.png](img%2FCopyOnWrite.png)

Пример 1:
```java
public class CopyOnWriteArrListEx1 {
    public static void main(String[] args) throws InterruptedException {
        // var list = new CopyOnWriteArrayList<>();
        var list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");

        Runnable runnable1 = () -> {
             var iterator = list.iterator();
             while (iterator.hasNext()){
                 try {
                     Thread.sleep(100);
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }

                 System.out.println(iterator.next());
             }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.remove(3);
            list.add("New element");
        };

        execute(list, runnable1, runnable2);
    }

    private static void execute(List<Object> list, Runnable runnable1, Runnable runnable2) throws InterruptedException {
        System.out.println(list);
        var thread1 = new Thread(runnable1);
        var thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(list);
    }
}
```
OUT:
```
[One, Two, Three, Four]
One
Exception in thread "Thread-0" java.util.ConcurrentModificationException
...
[One, Two, Three, New element]

```
Решение:
```var list = new CopyOnWriteArrayList<>();```

OUT:

```
[One, Two, Three, Four]
One
Two
Three
Four
[One, Two, Three, New element]
```

`var iterator = list.iterator();` - иттератор запоминает состояние коллекции на момент вызова. 
Что происходит в параллельных потоках ему не важно!

Каждый раз при изменении коллекции создается его копия
```java
list.remove(3);
list.add("New element");
```

**Вывод:** нужно использовать когда есть много операций чтения и мало операций изменения (каждый раз создается копия исходного list)

[Содержание](#content)
### 4. CopyOnWriteArraySet

Работает аналогично **CopyOnWriteArrayList**

Пример:
```java
public class CopyOnWriteArraySetEx {
    public static void main(String[] args) {
        Set<String> users = new CopyOnWriteArraySet<>();
        users.add("user_1");
        users.add("user_2");
        users.add("user_3");

        System.out.println(users);
    }
}
```

[Содержание](#content)
