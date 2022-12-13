package codeExample;

import java.util.stream.Stream;

public class App
{
    public static void main( String[] args )
    {
        var res = Stream.of("aaa", "bbss", "ss", "cdess")
                .peek(s -> System.out.println("filter1 Element: " + s))
                .filter(s -> s.contains("ss"))
                .filter(s -> s.length() > 2)
                .findFirst()
        ;

        System.out.println("Result:");
        System.out.println(res.orElse("not found"));;
    }
}
