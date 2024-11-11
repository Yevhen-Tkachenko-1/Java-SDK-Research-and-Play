package yevhent.demo.multithreading.concurrency.locks.abandoned;

import java.util.List;
import java.util.function.Function;

public class AbandonedLockDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println();
        System.out.println("Yevhen: Dear Philosophers,");
        System.out.println("        For you 3 people we have only 3 chopsticks. This seems to be enough for eating with some pauses.");
        System.out.println("        Let's have a dinner!");
        System.out.println();
        List<Philosopher> philosophers = eatSushi(Philosopher.ObliviousPhilosopher::new);
        Thread.sleep(5_000);
        long start = System.currentTimeMillis();
        System.out.println("\nYevhen: Checking if you ok ...");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(2_000);
            System.out.println("\nYevhen: another 2 hour passed ...");
        }
        System.out.println("\nYevhen: " + (System.currentTimeMillis() - start) / 1000 + " hours passed, are you ok?\n");
        for (Philosopher philosopher : philosophers) {
            philosopher.finishDinner();
        }
        Thread.sleep(2_000);
        System.out.println();
        System.out.println("Yevhen: Barron took 2 chopsticks with him when unexpectedly left dinner!");
        System.out.println("        So, Olivia and Steve were not able to eat with 1 available chopstick.");
        System.out.println("        Barron, this time, don't forget to put chopsticks back if you need to leave for urgent case:");
        System.out.println();
        eatSushi(Philosopher.CarefulPhilosopher::new);
        Thread.sleep(5_000);
        System.out.println();
        System.out.println("Yevhen: Great! Happy to know that :)");
        System.out.println();
    }

    static List<Philosopher> eatSushi(Function<String, Philosopher> factory) {
        List<ChopsticksPair> pairs = ChopsticksPair.getPrioritized();
        SushiDish sushiDish = new SushiDish(100);

        List<Philosopher> philosophers = List.of(factory.apply("Barron"), factory.apply("Olivia"), factory.apply("Steve"));
        for (int i = 0; i < philosophers.size(); i++) {
            philosophers.get(i).haveDinner(pairs.get(i), sushiDish);
        }
        return philosophers;
    }
}