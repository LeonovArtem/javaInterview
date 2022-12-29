# Open Api (Swagger)
Docs: https://springdoc.org/

[pom.xml](..%2F..%2F..%2Fspring%2Fpom.xml)
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
</dependency>
```
### Для SpringBoot 3
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.2</version>
</dependency>
```
Если подключен `Hibernate` (Иначе в рантайме будет ошибка)
```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>8.0.0.Final</version>
</dependency>
```

### Конфигурация
app.config
```lombok.config
# swagger-ui custom path
springdoc.swagger-ui.path=/api-docs

```
app.yml
```yaml
# Swagger
springdoc:
  swagger-ui:
    path: /api-docs
```