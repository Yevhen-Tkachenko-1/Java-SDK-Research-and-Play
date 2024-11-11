package yevhent.demo.multithreading.concurrency.locks.readwrite.calendar;

import java.util.Optional;

public class SimpleCalendar implements Calendar {

    protected static final String[] WEEKDAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    protected int currentWeekDay = 0;

    @Override
    public Optional<String> updateWeekDay(String modifier) {
        int actualWeekDay = currentWeekDay + 1;
        Optional<String> weekDay = weekDay(actualWeekDay);
        if (weekDay.isPresent()) {
            System.out.println(modifier + ": today is " + weekDay.get() + ", going to update ...");
            process(100);
            currentWeekDay = actualWeekDay;
            System.out.println(modifier + ": week day has updated to " + weekDay.get());
        } else {
            currentWeekDay = actualWeekDay;
            System.out.println(modifier + ": week has passed.");
        }
        return weekDay;
    }

    @Override
    public Optional<String> showWeekDay(String user) {
        process(75);
        Optional<String> weekDay = weekDay(currentWeekDay);
        weekDay.ifPresent(s -> System.out.println(user + ": I see today is " + s));
        return weekDay;
    }

    public static Optional<String> weekDay(int index) {
        if (index >= 7) {
            return Optional.empty();
        }
        return Optional.of(WEEKDAYS[index]);
    }

    protected void process(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
