# Mysql

[application.yml](..%2F..%2F..%2Fspring%2Fsrc%2Fmain%2Fresources%2Fapplication.yml)

```yaml
  datasource:
    hikari:
      data-source-properties:
        useServerPrepStmts: 'true'
        cacheServerConfiguration: 'true'
        prepStmtCacheSize: '250'
        prepStmtCacheSqlLimit: '2048'
        useLocalTransactionState: 'true'
        cacheResultSetMetadata: 'true'
        cachePrepStmts: 'true'
        rewriteBatchedStatements: 'true'
        elideSetAutoCommits: 'true'
        useLocalSessionState: 'true'
        maintainTimeStats: 'false'
      pool-name: ${spring.application.name}-pool
      transaction-isolation: TRANSACTION_READ_COMMITTED
      maximum-pool-size: '40'
    url: jdbc:mysql://${MYSQL_HOST:localhost}:${MYSQL_PORT:3311}/${MYSQL_DATABASE:test-db}?useUnicode=true&allowMultiQueries=true&characterEncoding=UTF-8&autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Moscow
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ${MYSQL_USER:user}
    password: ${MYSQL_PASSWORD:user}

  jpa:
    hibernate:
      naming-strategy: org.hibernate.cfg.DefaultComponentSafeNamingStrategy
      ddl-auto: update
    properties:
      hibernate:
        format_sql: 'true'
    open-in-view: 'false'
```
pom.xml

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- MySql -->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <scope>runtime</scope>
        </dependency>
```
