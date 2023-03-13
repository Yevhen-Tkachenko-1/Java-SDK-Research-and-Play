package java9;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewAPIs {

    public static void main(String[] args) {

        System.out.println(Set.of(1,2,3,4));
        System.out.println(List.of(1,2,3,4));
        System.out.println(Map.of(1,2,3,4));

        Stream<Optional<?>> streamOptional = List.of(Optional.of(1),
                Optional.empty(),
                Optional.of(2),
                Optional.empty(),
                Optional.of(3)).stream();

        Stream<?> stream = streamOptional.flatMap(Optional::stream);
        System.out.println(stream.collect(Collectors.toList()));
    }
}
