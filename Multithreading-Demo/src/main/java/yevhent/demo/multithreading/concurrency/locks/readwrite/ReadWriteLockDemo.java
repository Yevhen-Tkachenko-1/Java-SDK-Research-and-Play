package yevhent.demo.multithreading.concurrency.locks.readwrite;

import yevhent.demo.multithreading.concurrency.locks.readwrite.calendar.BlockingCalendar;
import yevhent.demo.multithreading.concurrency.locks.readwrite.calendar.Calendar;
import yevhent.demo.multithreading.concurrency.locks.readwrite.calendar.ReadWriteCalendar;
import yevhent.demo.multithreading.concurrency.locks.readwrite.calendar.SimpleCalendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        System.out.println("Yevhen: Let's test our Calendar access!");
        System.out.println("        1. Users are going to read weekday each day several times ...");
        System.out.println("        2. Clock will count down week day by day ...");
        System.out.println();
        passWeek(new SimpleCalendar());
        System.out.println();
        System.out.println("Yevhen: Works but not ideal!");
        System.out.println("        Some Users see the day before instead of actual.");
        System.out.println("        Now, let's use blocking lock ...");
        System.out.println();
        passWeek(new BlockingCalendar());
        System.out.println();
        System.out.println("Yevhen: Better, now users see right day!");
        System.out.println("        However, it became harder to access Calendar, let's try to increase throughput.");
        System.out.println("        Now, let's use read-write lock ...");
        System.out.println();
        passWeek(new ReadWriteCalendar());
        System.out.println();
        System.out.println("Yevhen: Great, now users see right day and they don't wait for each other!");
        System.out.println("        So, we have better throughput!");
        System.out.println();
    }

    static void passWeek(Calendar calendar) throws InterruptedException {
        Clock clock = new Clock(calendar);
        clock.start();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            users.add(new User("User-" + i, calendar));
        }
        for (User user : users) {
            user.start();
        }
        clock.join();
        for (User user : users) {
            user.join();
        }
        int visits = 0;
        for (User user : users) {
            visits += user.visits;
        }
        System.out.println("Users: we've seen day " + visits + " times!");
    }

    static class Clock extends Thread {

        private final Calendar calendar;

        Clock(Calendar calendar) {
            this.calendar = calendar;
        }

        public void run() {
            Optional<String> day = Optional.of("");
            while (day.isPresent()) {
                countDownOneDay();
                day = calendar.updateWeekDay("Clock");
            }
        }

        private void countDownOneDay() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class User extends Thread {

        private final String name;
        private final Calendar calendar;

        int visits = 0;

        User(String name, Calendar calendar) {
            this.name = name;
            this.calendar = calendar;
        }

        public void run() {
            Optional<String> day = Optional.of("");
            while (day.isPresent()) {
                goOnBusiness();
                day = calendar.showWeekDay(name);
                visits++;
            }
        }

        private void goOnBusiness() {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
