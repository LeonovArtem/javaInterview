package org.aleonov.javainteview.oop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class CompareExample {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 1_000_000_000; i++) {
            var person = new Person();
            person.setAge(i + 1);
            person.setId(i);
            person.setName("b");

            persons.add(person);
        }

        var start = System.currentTimeMillis();

        var res = persons
                .stream()
                .min(Person::compareTo)
                .get();

        var end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end - start));

        System.out.println(res);
    }
}

@Getter
@Setter
@ToString
class Person implements Comparable<Person> {
    private Integer id;
    private String name;
    private Integer age;

    @Override
    public int compareTo(Person person) {
        return Integer.compare(this.age, person.age);
    }
}
