package al.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.net.URISyntaxException;

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
