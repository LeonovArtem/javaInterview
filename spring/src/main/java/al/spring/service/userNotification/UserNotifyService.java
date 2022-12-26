package al.spring.service.userNotification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserNotifyService {
    private final List<UserNotification> notifications;

    public void notify(String message) {
        notifications.forEach(userNotification -> userNotification.sent(message));
    }
}
