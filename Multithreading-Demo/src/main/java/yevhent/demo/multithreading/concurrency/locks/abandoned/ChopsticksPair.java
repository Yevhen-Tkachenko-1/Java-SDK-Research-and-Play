package yevhent.demo.multithreading.concurrency.locks.abandoned;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopsticksPair {
    private final String name;

    private final Lock first;
    private final Lock second;

    public ChopsticksPair(String name, Lock first, Lock second) {
        this.name = name;
        this.first = first;
        this.second = second;
    }

    public boolean take() {
        if (first.tryLock()) {
            processing();
            if (second.tryLock()) {
                return true;
            } else {
                first.unlock();
            }
            processing();
        }
        return false;
    }

    public void release() {
        second.unlock();
        first.unlock();
    }

    protected void processing() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public static String toString(int first, int second) {
        return String.format("ChopsticksPair: [%d,%d]", first, second);
    }

    public static List<ChopsticksPair> getPrioritized() {
        List<Lock> allChopsticks = getAllChopsticks();
        return List.of(
                new ChopsticksPair(ChopsticksPair.toString(0, 1), allChopsticks.get(0), allChopsticks.get(1)),
                new ChopsticksPair(ChopsticksPair.toString(1, 2), allChopsticks.get(1), allChopsticks.get(2)),
                new ChopsticksPair(ChopsticksPair.toString(0, 2), allChopsticks.get(0), allChopsticks.get(2))
        );
    }

    static List<Lock> getAllChopsticks() {
        return List.of(new ReentrantLock(), new ReentrantLock(), new ReentrantLock());
    }
}