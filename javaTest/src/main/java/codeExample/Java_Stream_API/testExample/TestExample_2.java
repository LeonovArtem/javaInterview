package codeExample.Java_Stream_API.testExample;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

public class TestExample_2 {
    public static void main(String[] args) {
        var arr = List.of("one", "two");
        var upperArr = arr
                .stream()
                .map(String::toUpperCase)
                .map(s -> "|" + s + "|")
                .toList();

        System.out.println(arr);
        System.out.println(upperArr);

        var users = arr
                .stream()
                .map(User::new)
                .toList();

        System.out.println(users);
    }

    @ToString
    @AllArgsConstructor
    private static class User {
        private String name;
    }
}
