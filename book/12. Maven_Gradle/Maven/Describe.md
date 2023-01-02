# Maven

Docs: https://maven.apache.org/scm.html

Курсы: https://www.youtube.com/watch?v=QpaWl2-BsMY&list=PLnh8EajVFTl5fusY9MRBEOoLjbv8Trms5

![maven_1.png](img%2Fmaven_1.png)

![maven_source.png](img%2Fmaven_source.png)

```shell
mvn <plugin>:<goal>
```
Пример:

```shell
mvn compiler:help
```
![maven_plugin.png](img%2Fmaven_plugin.png)

`Плагин` - это просто Java класс который расширяет `Mojo`

`Mojo` - это просто класс с методом `execute()`

### Jvm arguments

```java
public class Arguments {
    public static void main(String[] args) {
        var env = System.getenv();
        System.out.printf(env.toString());
    }
}
```

### Maven plugin
### 1. Maven archetype
```shell
mvn archetype:help
```

```shell
mvn archetype:generate
```

Версионирование (Versions):
```
major.minor.increment
```
```
major.minor.increment-qualifier
```
```
// SNAPSHOT - это qualifier
1.2.5-SNAPSHOT
```
`SNAPSHOT` - это не релиз версия. 
Т.е не та что используется в продакшене (in progress version)

