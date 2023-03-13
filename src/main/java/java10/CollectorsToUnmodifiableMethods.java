package java10;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsToUnmodifiableMethods {

    public static void main(String[] args) {

        Collection<Integer> collection = List.of(1, 2, 3, 4);

        List<Integer> list = collection.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toUnmodifiableList());
        System.out.println(list);
        //list.add(5); // java.lang.UnsupportedOperationException

        Set<Integer> set = collection.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toUnmodifiableSet());
        System.out.println(set);
        //set.add(5); // java.lang.UnsupportedOperationException

        Map<Integer, String> map = collection.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toUnmodifiableMap(Function.identity(), String::valueOf));
        System.out.println(map);
        //map.put(5, "5"); // java.lang.UnsupportedOperationException
    }
}
