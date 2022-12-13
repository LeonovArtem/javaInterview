package codeExample.Java_Stream_API.testExample;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

public class TestExample_1 {
    public static void main(String[] args) {
        List<Artist> artists = List.of(
                new Artist().setCity("London"),
                new Artist().setCity("Moscow"),
                new Artist().setCity("London")
        );

        var filteredList = artists
                .stream()
                .peek(System.out::println)
                .filter(artist -> artist.getCity().equals("London"))
                .limit(1)
                .findFirst()
                .orElseThrow(RuntimeException::new);

        System.out.println(filteredList);
    }

    @Data
    @Accessors(chain = true)
    public static class Artist {
        private String city;
    }
}
