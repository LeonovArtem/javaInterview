# **Java Multithreading**

- [Отличие Thread от Runnable](#отличие-thread-от-runnable)
- [Поток-демон](#поток-демон)
- [Ключевое слово volatile (когерентность кэша)](#ключевое-слово-volatile)
- [Deadlock, Livelock, Lock Starvation](#deadlock-livelock-lock-starvation)
- [Ключевое слово synchronized](#ключевое-слово-synchronized)
- [Монитор(мьютекс)](#монитор)
- [wait, notify](#wait-notify)
- [Паттерн Consumer Producer](#паттерн-consumer-producer)
- [Прерывание потоков](#прерывание-потоков)
- [Callable и Future](#callable-и-future)
- [ReentrantLock](#reentrantlock)
- [Thread pool](topics/ThreadPool.md)
- [Concurrent Collections](topics/Collections.md)

## Отличие Thread от Runnable
```java
class MyThread extends Thread {
    
    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(i);
        }
    }
}
```

```java
class MyRunner implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(i);
        }
    }
}
```
```Thread``` - это абстракция над физическим потоком.

```Runnable``` - это абстракция над выполняемой задачей.

Плюс использования ```Runnable``` состоит в том, что это позволяет логически 
отделить выполнение задачи от логики управления потоками.

[Содержание](#java-multithreading)

## Поток-демон

Потоками-демонами называются потоки, работающие в фоновом режиме для нашей программы.

В Java процесс завершается тогда, когда завершается последний его поток. 
Даже если метод main() уже завершился, но еще выполняются порожденные им потоки, система будет ждать их завершения.
Однако это правило не относится к особому виду потоков – демонам. 
Если завершился последний обычный поток процесса, и остались только потоки-демоны, то они будут принудительно завершены и выполнение процесса закончится. 

Чаще всего потоки-демоны используются для выполнения фоновых задач, обслуживающих процесс в течение его жизни.

```thread.setDaemon(true);``` - пометить поток как демон

```java
public class DaemonExample {
    public static void main(String[] args) throws InterruptedException {
        runDaemon();
        runNotDaemon();
        System.out.println("Main is DONE!");
    }

    private static void runNotDaemon() {
        var thread = new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("NOT Daemon done");
        });
        thread.start();
    }

    /**
     * Если завершился последний обычный поток процесса, и остались только потоки-демоны,
     * то они будут принудительно завершены и выполнение процесса закончится
     */
    private static void runDaemon() {
        var thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Daemon is done!");
        });
        thread.setDaemon(true);
        thread.start();
    }
}
```
[Содержание](#java-multithreading)

## Ключевое слово volatile
```volatile``` - используется когда один поток пишет в переменную, а все остальные потоки
читают эту переменную. Позволяет не кэшировать переменную в кэше ядра(когерентность кэша).

![cache_coherence.jpeg](img%2Fcache_coherence.jpeg)

Потоки могут распределиться по разным ядрам процессора и закэшировать значение переменной,
значение которой может поменяться. Вследствие чего, у потоков будет не актуальное значение
это переменной.

## Deadlock, Livelock, Lock Starvation
![problem_locks.png](img%2Fproblem_locks.png)
### DeadLock
`DeadLock` - два потока ждут друг друга до бесконечности. При этом потоки ничего не делают.
Проблема вознивает, когда мы синхронизируемся в двух потоках в разном порядке

![java-deadlock.png](img%2Fjava-deadlock.png)

```java
public class DeadLockEx {
    public static void main(String[] args) throws InterruptedException {
        Object lock1 = new Object();
        Object lock2 = new Object();
        var thread1 = new Thread(() -> Runner.run(lock1, lock2));
        var thread2 = new Thread(() -> Runner.run(lock2, lock1));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Main done");
    }

    private class Runner {
        public static void run(Object ob1, Object ob2) {
            System.out.println("Попытка хахватить монитор объекта 1");
            synchronized (ob1) {
                System.out.println("монитор объекта 1 захвачен");
                synchronized (ob2) {
                    System.out.println("Run..");
                }
            }
        }
    }
}
```

## Ключевое слово synchronized
```synchronized``` - работает таким образом, что только один поток в момент времени может
получить доступ к выполнению тела метода.
```java
public synchronized void execute() {
    /*...*/
}
```

```java
public void execute() {
    synchronized (this) {
        /*...*/
    }
}
```
**Блокировка на уровне объекта** – механизм, когда вы хотите синхронизировать non-static метод или non-static блок кода таким образом, что только один поток сможет выполнить блок кода в данном экземпляре класса. 
Это нужно всегда делать, чтобы сделать экземпляр класса потокобезопасным.

**Блокировка на уровне класса** - предотвращает вход нескольких потоков в синхронизированный блок для всех доступных экземпляров класса. Например, если есть 100 экземпляров класса DemoClass, то только 1 поток сможет выполнить demoMethod () используя одну из переменных в определенный момент времени. 
Это должно быть всегда сделано, что бы обеспечить безопасность статического потока.

Правила синхронизации:
https://howtodoinjava.com/java/multi-threading/object-vs-class-level-locking/
1. НЕ использовать synchronized с **конструктором** - оибка компиляции.
```java
    static class User {
        private Integer id;
        private String name;
        
        // Так нельзя
        public synchronized User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }
```
[Содержание](#java-multithreading)

### Монитор

Несколько нитей могут мешать друг другу при обращении к одним и тем же данным.
Для решения этой проблемы придуман **мьютекс** (он же **монитор**). 
Он имеет два состояния — объект занят и объект свободен. 

**Монитор**(мьютекс) — высокоуровневый механизм взаимодействия и синхронизации потоков, обеспечивающий доступ к неразделяемым ресурсам.

Когда одна нить заходит внутрь блока кода, помеченного словом synchronized, то Java-машина тут же блокирует мьютекс у объекта, который указан в круглых скобках после слова synchronized. 
Больше ни одна нить не сможет зайти в этот блок, пока наша нить его не покинет. 
Как только наша нить выйдет из блока, помеченного synchronized, то мьютекс тут же автоматически разблокируется и будет свободен для захвата другой нитью. 
Если же мьютекс был занят, то наша нить будет стоять на месте и ждать когда он освободится.

**Мьютекс встроен в класс Object и следовательно он есть у каждого объекта.**

[Содержание](#java-multithreading)

## wait, notify
```wait()``` - освобождает монитор и переводит вызывающий поток в состояние ожидания до тех пор,
пока другой поток не вызовет метод notify().

```notify()``` - продолжает работу потока, у которого ранее был вызван метод wait().

Пример:
```java
class Worker {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Start produce...");
            this.wait();
            System.out.println("Finish produce...");
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this) {
            System.out.println("Start consume...");
            Thread.sleep(2000);
            System.out.println("Notify...");
            this.notify();
        }
    }
}
```

Output:
```
Start produce...
Start consume...
Notify...
Finish produce...
```
[Содержание](#java-multithreading)

## Паттерн Consumer Producer
```java
class Worker {
    private final Queue<Integer> queue = new LinkedList<>();
    private final Integer MAX_QUEUE_SIZE = 10;


    public void produce() throws InterruptedException {
        int payload = 0;

        while (true) {
            synchronized (this) {
                if (queue.size() == MAX_QUEUE_SIZE) {
                    this.wait();
                }

                queue.offer(payload++);
                this.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (queue.size() == 0) {
                    this.wait();
                }

                System.out.println("Poll a payload from queue: " + queue.poll() + ". Current size is: " + queue.size());
                this.notify();
            }

            Thread.sleep(1000);
        }
    }
}
```
[Содержание](#java-multithreading)

## Прерывание потоков
Прерывания позволяют завершить один поток из другого.
```java
public class Example {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
        });

        thread.start();
        Thread.sleep(1500);
        thread.interrupt();
        thread.join();
    }
}
```
[Содержание](#java-multithreading)

## Callable и Future
Интерфейс ```Callable``` позволяет выбрасывать исключение и возвращать результат выполнения потока.
 ```Future``` используется для получения результата работы потока.

```java
public class Example {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();

                int randomInt = random.nextInt();

                if (randomInt == 5) {
                    throw new Exception("Random number is 5");
                }

                return randomInt;
            }
        });

        executorService.shutdown();

        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
```

[Содержание](#java-multithreading)

## ReentrantLock
```ReentrantLock``` - аналог synchronized.
```java
class Runner {
    private int i;

    Lock lock = new ReentrantLock();

    public void increase() {
        lock.lock();
        for (int i = 0; i < 10000; i++) {
            this.i++;
        }
        lock.unlock();
    }

    public void firstThread() {
        this.increase();
    }

    public void secondThread() {
        this.increase();
    }

    public int getI() {
        return i;
    }
}
```

Для того, что бы отпустить блок, метод ```unlock()``` нужно вызвать столько же раз, 
сколько вызвали ```lock()```.

```ReentrantLock``` можно использовать, когда в разных потоках, необходимо забирать
локи в разных порядках.

[Содержание](#java-multithreading)
