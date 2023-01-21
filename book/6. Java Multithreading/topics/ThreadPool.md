# Thread pool

- [FixedThreadPool](#fixedthreadpool)
- [ScheduleThreadPool](#schedulethreadpool)
- [CachedThreadPool](#cachedthreadpool)

## FixedThreadPool

Thread pool - пул потоков, позволяет выполнять задания в N потоках.

![thread_pool_1.png](..%2Fimg%2Fthread_pool_1.png)

![thread_pool_2.png](..%2Fimg%2Fthread_pool_2.png)

Пример 1:

`executorService.execute(Runnable ..)`

```java
public class Example {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Task(i));
        }

        executorService.shutdown();
    }
}

class Task implements Runnable {
    private final int id;

    Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.printf("The task with id %d executed \n", id);
    }
}
```

```java
public class ThreadPoolEx1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            int numTask = i;
            // Первый способ
            executorService.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                var name = Thread.currentThread().getName();
                System.out.println(name + " :Task n: " + numTask + " is done");
            });
        }
        
        System.out.println("Main is down");
    }
}
```
Пример 2:

`executorService.submit` - Добавить в очередь на выполнение

`executorService.submit` - Выполнить таски

```java
public class ThreadPoolEx2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            int numTask = i;
            // Добавить в очередь на выполнение
            executorService.submit(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                var name = Thread.currentThread().getName();
                System.out.println(name + " :Task n: " + numTask + " is done");
            });
        }
        
        // Выполнить таски
        executorService.shutdown();
        
        // Похож на .join()
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Main is down");
    }
}
```

[Содержание](#thread-pool)

## ScheduleThreadPool

![thread_pool_3.png](..%2Fimg%2Fthread_pool_3.png)

```java
public class ScheduleThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < 4; i++) {
            scheduledThreadPool.schedule(new MyTask(i), 3, TimeUnit.SECONDS);
        }

        System.out.println("Main is down");
    }

    private static class MyTask implements Runnable {
        private final Integer numTask;

        public MyTask(Integer numTask) {
            this.numTask = numTask;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            var name = Thread.currentThread().getName();
            System.out.println(name + " :Task " + numTask + " is done");
        }
    }

}
```
[Содержание](#thread-pool)

## CachedThreadPool

![thread_pool_4.png](..%2Fimg%2Fthread_pool_4.png)
Пример
```java
public class CashedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService scheduledThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            scheduledThreadPool.execute(new MyTask(i));
        }

        System.out.println("Main is down");
    }

    private static class MyTask implements Runnable {
        private final Integer numTask;

        public MyTask(Integer numTask) {
            this.numTask = numTask;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            var name = Thread.currentThread().getName();
            System.out.println(name + " :Task " + numTask + " is done");
        }
    }

}
```
[Содержание](#thread-pool)