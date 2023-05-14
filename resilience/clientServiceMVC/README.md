### Circular Breaker


```yaml
resilience4j.circuitbreaker:
  configs:
    default:
      registerHealthIndicator: true
      failureRateThreshold: 50
      slidingWindowSize: 10
      minimumNumberOfCalls: 5
      permittedNumberOfCallsInHalfOpenState: 3
      automaticTransitionFromOpenToHalfOpenEnabled: true
      waitDurationInOpenState: 5s
      eventConsumerBufferSize: 10
      recordExceptions:
        - org.springframework.web.client.HttpServerErrorException
        - java.util.concurrent.TimeoutException
        - java.io.IOExceptio
```
1. `registerHealthIndicator: true` - означат что все сервисы будут добавлены 

в /actuator/health

![img_1.png](img%2Fimg_1.png)

```java
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final RestTemplate restTemplate;
    private static final String ORDERS_BASE_URL = "http://localhost:9090/api/v1/orders";

    @GetMapping("rest")
    @CircuitBreaker(name = "circularService1", fallbackMethod = "getOrdersMock")
    public List<?> getByRestClient() {
        return restTemplate
                .getForObject(ORDERS_BASE_URL, ArrayList.class);
    }

    public List<?> getOrdersMock(Throwable e) {
        return List.of(
                new OrderDto(0, "it is mock_1"),
                new OrderDto(0, "it is mock_2")
        );
    }
}
```

![shema.jpg](img%2Fshema.jpg)