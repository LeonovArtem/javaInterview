package org.aleonov.javainteview.oop;

import lombok.AllArgsConstructor;
import lombok.Data;

public class CloneExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        var user = new User(1,"Artem");
        System.out.println(user);

        changeUserName(user);

        System.out.println(user);

        var cloneUser = user.clone();
        System.out.println(cloneUser);
    }

    /**
     * Объекты передаются по ссылке. Прмитивы по значению
     */
    private static void changeUserName(final User user){
        user.setName("NEW_NAME");
    }

}

@Data
@AllArgsConstructor
class User implements Cloneable{
    private final Integer id;
    private String name;

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}
