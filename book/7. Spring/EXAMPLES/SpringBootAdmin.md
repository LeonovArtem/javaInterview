# Spring boot Admin

Доступен:
http://localhost:${server.port}/admin

pom.xml
```yaml
        <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-starter-client</artifactId>
        </dependency>
        
        <dependency>
            <groupId>de.codecentric</groupId>
            <artifactId>spring-boot-admin-starter-server</artifactId>
        </dependency>

        <!-- actuator -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```

```java
// EnableAdminServer - обязательно
@EnableAdminServer
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```
В application.yml
```yaml
server:
  port: 5006

spring:
  application:
    name: DemoApp
  boot:
    admin:
      client:
        url: http://localhost:${server.port}/admin
      context-path: /admin

management:
  endpoints:
    web:
      exposure:
        include: "*"

```