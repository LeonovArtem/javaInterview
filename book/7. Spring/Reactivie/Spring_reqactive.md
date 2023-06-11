## Spring WebFlux
pom.xml
```xml
    <dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-r2dbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webflux</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <!-- MySql Drivers -->
    <dependency>
        <groupId>dev.miku</groupId>
        <artifactId>r2dbc-mysql</artifactId>
        <version>0.8.2.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.projectreactor</groupId>
        <artifactId>reactor-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies> 
```
[application.properties](..%2F..%2F..%2FreactiveSpring%2Fsrc%2Fmain%2Fresources%2Fapplication.properties)

```yaml
server.port=8082

# MySQL properties
spring.r2dbc.url=r2dbc:mysql://${MYSQL_HOST:localhost}:${MYSQL_PORT:3311}/${MYSQL_DATABASE:reactive-db}?serverTimezone=Europe/Moscow&createDatabaseIfNotExist=true
spring.r2dbc.username=${MYSQL_USER:root}
spring.r2dbc.password=${MYSQL_PASSWORD:root}

# Logging
logging.level.org.springframework.data.r2dbc=DEBUG
```