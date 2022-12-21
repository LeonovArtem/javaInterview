# H2 Database

pom.xml
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- H2 Database -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
```

[application.yml](..%2F..%2F..%2Fspring%2Fsrc%2Fmain%2Fresources%2Fapplication.yml)
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:test_db
    username: user
    password: user
    driverClassName: org.h2.Driver
  jpa:
    spring.jpa.database-platform: org.hibernate.dialect.H2Dialect
```
