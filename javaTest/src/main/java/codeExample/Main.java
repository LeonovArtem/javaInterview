package codeExample;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Notification> notifications = List.of(
                new EmailNotification(),
                new SmsNotification()
        );

        notifications.forEach(Notification::send);
    }

    abstract static class Notification {
        public abstract void send();
    }

    static class SmsNotification extends  Notification{
        @Override
        public void send() {
            System.out.println("Sms is sent");
        }
    }

    static class EmailNotification extends Notification {
        @Override
        public void send() {
            System.out.println("Email is sent");
        }
    }
}
