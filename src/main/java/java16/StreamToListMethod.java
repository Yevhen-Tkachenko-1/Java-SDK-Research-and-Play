package java16;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamToListMethod {

    public static void main(String[] args) {

        List<Integer> previous = Stream.of(1, 2, 3, 4 ,5).collect(Collectors.toList());
        List<Integer> current = Stream.of(1, 2, 3, 4 ,5).toList();

    }
}
