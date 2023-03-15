package java11;

import java.util.List;
import java.util.stream.Collectors;

public class StringMethods {

    public static void main(String[] args) {

        String multilineString = "We help \n \n developers \n explore Java.";

        List<String> lines = multilineString.repeat(2)
                .lines()
                .filter(line -> !line.isBlank())
                .map(String::strip)
                .collect(Collectors.toList());

        System.out.println(lines);

        System.out.println();
        String indented = "\t\tfirst line\n\t\t\tsecond line\n\t\tthird line";
        System.out.println(indented);
        System.out.println(indented.stripIndent());

        System.out.println();
        String strip = "   \t   middle   \t   ";
        System.out.println("start " + (strip.stripLeading()) + " end");
        System.out.println("start " + (strip.stripTrailing()) + " end");
    }


}
