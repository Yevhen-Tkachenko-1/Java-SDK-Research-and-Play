package version.java14;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

// Standard
public class SwitchExpression {

    public static void main(String[] args) {

        // previous
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        String dayType = "";
        switch (dayOfWeek) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                dayType = "Working Day";
                break;
            case SATURDAY:
            case SUNDAY:
                dayType = "Day Off";
        }
        System.out.println(dayType);

        // new
        dayType = switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Working Day";
            case SATURDAY, SUNDAY -> "Weekend";
        };
        System.out.println(dayType);

        switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> System.out.println("Working Day");
            case SATURDAY, SUNDAY -> {
                System.out.println("Day Off");
                System.out.println("SATURDAY or SUNDAY");
            }
        }

        dayType = switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY:
                yield "Working Day";
            case SATURDAY, SUNDAY:
                yield "Weekend";
        };
        System.out.println(dayType);


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
