package al.spring.service.userNotification;

import org.springframework.stereotype.Service;

@Service
public class SmsNotification implements UserNotification {
    @Override
    public void sent(String message) {
        System.out.println("SMS: " + message);
    }
}
