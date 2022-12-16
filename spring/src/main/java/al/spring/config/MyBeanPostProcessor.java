package al.spring.config;

import al.spring.service.TelegramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TelegramService) {
            System.out.println("postProcessBeforeInitialization");
        }

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TelegramService) {
            System.out.println("postProcessAfterInitialization");
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
