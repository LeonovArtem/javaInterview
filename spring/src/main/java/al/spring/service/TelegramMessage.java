package al.spring.service;

import org.springframework.stereotype.Component;

@Component
public class TelegramMessage {

    public void sendMessage(String message) {
        System.out.println("Send to Telegram:" + message);
    }
}
