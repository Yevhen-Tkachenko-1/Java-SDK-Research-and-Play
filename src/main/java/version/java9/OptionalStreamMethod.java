package version.java9;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalStreamMethod {

    public static void main(String[] args) {

        Stream<Optional<?>> streamOptional = List.of(Optional.of(1),
                Optional.empty(),
                Optional.of(2),
                Optional.empty(),
                Optional.of(3)).stream();

        Stream<?> stream = streamOptional.flatMap(Optional::stream);
        System.out.println(stream.collect(Collectors.toList()));
    }
}
