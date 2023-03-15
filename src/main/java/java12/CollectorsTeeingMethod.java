package java12;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsTeeingMethod {

    public static void main(String[] args) {

        double mean = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.teeing(
                            Collectors.summingDouble(i -> i),
                            Collectors.counting(),
                            (sum, count) -> sum / count));
        System.out.println(mean);

        Integer median = Stream.of(5, 4, 8, 8, 3, 1, 2, 3, 0, 7, 3, 10, 14)
                .sorted() // 0, 1, 2, 3, 3, 3, 4, 5, 7, 8, 8, 10, 14
                .collect(Collectors.teeing(
                            Collectors.toList(),
                            Collectors.counting(),
                            (list, count) -> list.get((int) (count/2))));
        System.out.println(median);
    }
}
