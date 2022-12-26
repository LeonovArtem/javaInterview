package al.spring.service.userNotification;

import org.springframework.stereotype.Service;

@Service
public class EmailNotification implements UserNotification {
    @Override
    public void sent(String message) {
        System.out.println("Email: " + message);
    }
}
