package yevhent.demo.multithreading.concurrency.locks.readwrite.calendar;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingCalendar extends SimpleCalendar {

    private static final Lock lock = new ReentrantLock();

    @Override
    public Optional<String> updateWeekDay(String modifier) {
        try {
            lock.lock();
            return super.updateWeekDay(modifier);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Optional<String> showWeekDay(String user) {
        try {
            lock.lock();
            return super.showWeekDay(user);
        } finally {
            lock.unlock();
        }
    }
}
