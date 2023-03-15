package java13;

import java.util.List;
import java.util.stream.Collectors;

public class YieldSwitchExpression {

    public static void main(String[] args) {

        List<Integer> list = List.of(1, 3, 4, 6, 8, 9);
        String action = "average";

        Integer result = switch (action){
            case "min" -> list.stream().collect(Collectors.summarizingInt(value -> value)).getMin();
            case "max" -> list.stream().collect(Collectors.summarizingInt(value -> value)).getMax();
            case "sum" -> Math.toIntExact(list.stream().collect(Collectors.summarizingInt(value -> value)).getSum());
            case "average" -> {
                // multiline statement
                Integer sum = Math.toIntExact(list.stream().collect(Collectors.summarizingInt(value -> value)).getSum());
                // return using yield
                yield sum/list.size();
            }
            default -> throw new IllegalStateException("Unexpected value: " + action);
        };
        System.out.println(result);

    }
}
