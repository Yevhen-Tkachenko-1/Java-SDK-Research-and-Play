package version.java12;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SwitchExpression {

    public static void main(String[] args) {

        // previous
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        String typeOfDay = "";
        switch (dayOfWeek) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                typeOfDay = "Working Day";
                break;
            case SATURDAY:
            case SUNDAY:
                typeOfDay = "Day Off";
        }
        System.out.println(typeOfDay);

        // new
        typeOfDay = switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Working Day";
            case SATURDAY, SUNDAY -> "Day Off";
        };
        System.out.println(typeOfDay);

        switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> System.out.println("Working Day");
            case SATURDAY, SUNDAY -> {
                System.out.println("Day Off");
                System.out.println("SATURDAY or SUNDAY");
            }
        }
    }
}
