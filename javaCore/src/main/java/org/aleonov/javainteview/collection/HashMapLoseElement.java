package org.aleonov.javainteview.collection;

import lombok.*;

import java.util.HashMap;
import java.util.Objects;

// В каком случае может быть потерян элемент в HashMap?
public class HashMapLoseElement {
    public static void main(String[] args) {
        var user1 = new User(1, "artem");
        var user2 = new User(2, "admin");

        HashMap<User, Integer> map = new HashMap<>();

        map.put(user1, user1.getId());
        map.put(user2, user2.getId());

        user1.setName("NEW NAME");
        System.out.println("User_1: " + map.get(user1));
        System.out.println("User_2: " +map.get(user2));

        System.out.println(map);
    }

}

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
class User {
    private Integer id;
    private String name;
}
