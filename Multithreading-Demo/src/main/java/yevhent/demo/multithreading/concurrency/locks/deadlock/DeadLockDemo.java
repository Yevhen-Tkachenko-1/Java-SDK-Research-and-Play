package yevhent.demo.multithreading.concurrency.locks.deadlock;

import java.util.List;

public class DeadLockDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println();
        System.out.println("Yevhen: Dear Philosophers,");
        System.out.println("        For you 3 people we have only 3 chopsticks. This seems to be enough for eating with some pauses.");
        System.out.println("        Let's have a dinner!");
        System.out.println();
        List<Philosopher> philosophers = eatSushi(ChopsticksPair.getCircular());
        Thread.sleep(2_000);
        long start = System.currentTimeMillis();
        System.out.println();
        System.out.println("Yevhen: Checking if you ok ...");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(2_000);
            System.out.println("Yevhen: another 2 hour passed ...");
        }
        System.out.println("Yevhen: " + (System.currentTimeMillis() - start) / 1000 + " hours passed, are you ok?");
        System.out.println();
        for (Philosopher philosopher : philosophers) {
            philosopher.interrupt();
        }
        Thread.sleep(2_000);
        System.out.println();
        System.out.println("Yevhen: Unfortunately, you didn't manage to use 3 Chopsticks together,");
        System.out.println("        Let's have one more dinner with another rules!");
        System.out.println();
        eatSushi(ChopsticksPair.getPrioritized());
        Thread.sleep(2_000);
        System.out.println();
        System.out.println("Yevhen: Great! Happy to know that.");
        System.out.println();
    }

    static int sushiCount;

    static List<Philosopher> eatSushi(List<ChopsticksPair> pairs) {
        sushiCount = 100;
        Philosopher barron = new Philosopher("Barron", pairs.get(0));
        Philosopher olivia = new Philosopher("Olivia", pairs.get(1));
        Philosopher steve = new Philosopher("Steve", pairs.get(2));
        List<Philosopher> philosophers = List.of(barron, olivia, steve);
        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }
        return philosophers;
    }

    static class Philosopher extends Thread {

        private final ChopsticksPair chopsticks;

        public Philosopher(String name, ChopsticksPair chopsticks) {
            this.setName(name);
            this.chopsticks = chopsticks;
        }

        public void run() {

            System.out.println(this.getName() + ": I got " + chopsticks);

            int eaten = 0;
            while (sushiCount > 0) {
                try {
                    chopsticks.take();
                    if (sushiCount > 0) {
                        sushiCount--;
                        eaten++;
                        System.out.println(this.getName() + ": took a piece! Sushi remaining: " + sushiCount);
                    }
                    chopsticks.release();
                } catch (InterruptedException e) {
                    System.out.println(this.getName() + ": something went wrong! Finishing eating...");
                    System.out.println(this.getName() + ": ate " + eaten + " pieces of sushi. I'm still hungry!");
                    return;
                }
            }
            System.out.println(this.getName() + ": ate " + eaten + " pieces of sushi. I'm so sated!");
        }
    }
}