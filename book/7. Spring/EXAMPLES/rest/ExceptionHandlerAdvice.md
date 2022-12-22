# Spring Boot 3

1. Форматирование Response

```java

@RestController
@RequestMapping("post")
@RequiredArgsConstructor
public class PostController {
    @GetMapping("{id}")
    public Post findById(@PathVariable int id) {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new PostNotFoundException(id))
                ;
    }
}

```
ExceptionHandlerAdvice:
```java
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(PostNotFoundException.class)
    public ProblemDetail handlePostNotFoundException(PostNotFoundException e) throws URISyntaxException {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setProperty("postId", e.getPostId());
        problemDetail.setType(new URI("http:://localhost/problems/post/not-found"));

        return problemDetail;
    }
}
```


PostNotFoundException:
```java
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@AllArgsConstructor
// @ResponseStatus - не обязательно. Будет работать и без него. Нужен если используем без ExceptionHandlerAdvice! 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException{
    private Integer postId;
}
```


[application.yml](..%2F..%2F..%2F..%2Fspring%2Fsrc%2Fmain%2Fresources%2Fapplication.yml)

```yaml
spring:
  webflux:
    problemdetails:
      enabled: true
```