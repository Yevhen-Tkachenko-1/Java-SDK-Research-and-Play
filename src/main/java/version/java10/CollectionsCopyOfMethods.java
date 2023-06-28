package version.java10;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsCopyOfMethods {

    public static void main(String[] args) {

        Collection<Integer> collection = List.of(1, 2, 3, 4);

        List<Integer> copyList = List.copyOf(collection);
        copyList.add(5); // java.lang.UnsupportedOperationException

        Set<Integer> copySet = Set.copyOf(collection);
        copySet.add(5); // java.lang.UnsupportedOperationException

        Map<Integer, String> map = Map.of(1, "1", 2, "2");
        Map<Integer, String> mapCopy = Map.copyOf(map);
        mapCopy.put(3, "3"); // java.lang.UnsupportedOperationException
    }
}
