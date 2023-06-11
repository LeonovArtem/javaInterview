# Этапы создания бинов

```java
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class TelegramMessage implements InitializingBean {

    public void sendMessage(String message) {
        System.out.println("Send to Telegram:" + message);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // это один из хуков жизненного цикла
    }
}
```

Способы добавить свою логику:

1. InitializingBean ( 6. afterPropertiesSet)
2. Aware интерфейсы
3. BeanPostProcessor
4. Custom method (xml or PostConstruct)

```java
package al.spring.service;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class TelegramService implements
        InitializingBean,
        BeanFactoryAware,
        BeanClassLoaderAware,
        BeanNameAware,
        ApplicationContextAware {
    @Autowired

    // 1
    public TelegramService() {
        System.out.println("1. Constructor");
    }

    // 2
    @Override
    public void setBeanName(String name) {
        System.out.println("2. setBeanName");
        System.out.println("bean name: " + name);
    }

    // 3
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("3. setBeanClassLoader");
    }

    // 4
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("4. setBeanFactory");
    }

    // 5
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("5. setApplicationContext");
    }

    // 6
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("6. afterPropertiesSet");
    }
}
```

```
1. Constructor
2. setBeanName
bean name: telegramService
3. setBeanClassLoader
4. setBeanFactory
5. setApplicationContext
postProcessBeforeInitialization
6. afterPropertiesSet
postProcessAfterInitialization
```