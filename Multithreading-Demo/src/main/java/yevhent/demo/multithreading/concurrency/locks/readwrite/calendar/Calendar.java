package yevhent.demo.multithreading.concurrency.locks.readwrite.calendar;

import java.util.Optional;

public interface Calendar {

    /**
     * Should be Thread safe. Returns null when current week is passed.
     */
    Optional<String> updateWeekDay(String modifier);

    /**
     * Should be Thread safe. Returns null when current week is passed.
     */
    Optional<String> showWeekDay(String user);
}