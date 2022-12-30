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

### Swagger Аннотации

* @Tag(name = "Пользователи")
* @Operation
* @ApiResponse
* @Schema

```java
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor

@Tag(name = "Пользователи")
public class UserController {

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Найти пользователя по ID", responses = {
            @ApiResponse(responseCode = "200", description = "Пользователь", content = @Content(
                    schema = @Schema(implementation = User.class)
            )),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content(
                    schema = @Schema(implementation = User.class)
            )),
    })
    public User findById(@PathVariable int id) {
        return userService.findById(id);
    }
}
```