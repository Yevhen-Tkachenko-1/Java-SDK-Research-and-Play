package yevhent.demo.multithreading.concurrency.locks.livelock;

import java.util.Random;

public interface Philosopher {

    void haveDinner(ChopsticksPair pair, SushiDish dish);

    void finishDinner();

    class ImpatientPhilosopher extends Thread implements Philosopher {

        private ChopsticksPair chopsticks;
        private SushiDish sushiDish;

        public ImpatientPhilosopher(String name) {
            this.setName(name);
        }

        @Override
        public void haveDinner(ChopsticksPair chopsticks, SushiDish sushiDish) {
            this.chopsticks = chopsticks;
            this.sushiDish = sushiDish;
            start();
        }

        @Override
        public void finishDinner() {
            interrupt();
        }

        public void run() {

            System.out.println(this.getName() + ": I got " + chopsticks);

            int eaten = 0;
            while (sushiDish.hasSushi()) {
                try {
                    if (chopsticks.take()) {
                        eatSushi();
                        eaten++;
                        chopsticks.release();
                    } else {
                        tryAgain();
                    }
                } catch (Exception e) {
                    System.out.println(this.getName() + ": something went wrong! Finishing eating...");
                    System.out.println(this.getName() + ": ate " + eaten + " pieces of sushi. I'm still hungry!");
                    return;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(this.getName() + ": ate " + eaten + " pieces of sushi. Perfect, I'm so sated!");
        }

        protected void eatSushi() {
            int sushiRemaining = sushiDish.eatSushi();
            System.out.println(this.getName() + ": took a piece! Sushi remaining: " + sushiRemaining);
        }

        protected void tryAgain() {
            System.out.print(this.getName() + ": not my turn! Going to check again immediately... ");
        }
    }

    class PatientPhilosopher extends ImpatientPhilosopher {

        private final Random rps = new Random();

        public PatientPhilosopher(String name) {
            super(name);
        }

        @Override
        protected void tryAgain() {
            System.out.print(this.getName() + ": not my turn! Will check a bit later... ");
            try {
                Thread.sleep(rps.nextInt(10, 100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}