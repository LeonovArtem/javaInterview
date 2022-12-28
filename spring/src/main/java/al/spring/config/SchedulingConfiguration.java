package al.spring.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Profile("!test")
@Configuration
@EnableScheduling
@ConditionalOnProperty(
    value = "app.scheduling.enable", havingValue = "true", matchIfMissing = true
)
public class SchedulingConfiguration {
    /*_*/
}
