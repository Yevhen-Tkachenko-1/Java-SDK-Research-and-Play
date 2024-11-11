package yevhent.demo.multithreading.concurrency.locks.readwrite.calendar;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCalendar extends SimpleCalendar {

    private static final ReentrantReadWriteLock marker = new ReentrantReadWriteLock();
    private static final Lock readMarker = marker.readLock();
    private static final Lock writeMarker = marker.writeLock();

    @Override
    public Optional<String> updateWeekDay(String modifier) {
        try {
            writeMarker.lock();
            return super.updateWeekDay(modifier);
        } finally {
            writeMarker.unlock();
        }
    }

    @Override
    public Optional<String> showWeekDay(String user) {
        try {
            readMarker.lock();
            return super.showWeekDay(user);
        } finally {
            readMarker.unlock();
        }
    }
}
