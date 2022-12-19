# Java Core

1. What are generics and what is type erasing?

   
    It is the process of the compiler erasing this type for runtime.
   
    So that is the reason for backward compatibility.

    We will get an error on compilation.



# Solid

_Solid is an acronym for five design principles to make our application design more understandable and maintainable._

1. **Single Responsibility** - _Class should do only one thing. Class must have one responsibility._
2. **Open closed principal** - _A class should be open for extension and close for any modifications._
3. **Liskov Substitution principle** - _Subtypes must be substitutable for their base types. (Подтипы должны быть взаимозаменяемыми для своих базовых типов.)_

- If a program is using a Base class, then the Base class can be replaced on a Child class without affecting.  

```java
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
```
Bad example:

```java
void drawShape(Shape shape) {
   if (shape instanceof Square) {
       drawSquare((Square) shape);
   } else {
       drawCircle((Circle) shape);
   }
}
```
4. **Interface segregation** - 