# Security
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    // Разрешить все запросы
                    auth.anyRequest().permitAll();
                    
                    // Разрешить запросы подходящие по паттерну
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/user/**").permitAll();
                    
                    // Разрешить все авторизованные запросы
                    auth.anyRequest().authenticated();
                } )
                .build();
    }
}
```