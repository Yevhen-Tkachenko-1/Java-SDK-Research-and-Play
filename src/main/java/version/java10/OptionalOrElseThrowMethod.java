package version.java10;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class OptionalOrElseThrowMethod {

    public static void main(String[] args) {

        Optional<String> empty = Optional.empty();
        //String s1 = empty.orElseThrow(); // java.util.NoSuchElementException: No value present
        //String s2 = empty.orElseThrow(()-> new RuntimeException("Custom exception")); // java.lang.RuntimeException: Custom exception

        Collection<Integer> collection = List.of(1, 2, 3, 4);
        Integer value = collection.stream()
                .filter(i -> i > 5)
                .findFirst()
                .orElseThrow(); // java.util.NoSuchElementException: No value present
    }
}
