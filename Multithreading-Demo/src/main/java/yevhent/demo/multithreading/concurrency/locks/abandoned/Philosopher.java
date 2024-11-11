package yevhent.demo.multithreading.concurrency.locks.abandoned;

import java.util.Random;

public interface Philosopher {

    void haveDinner(ChopsticksPair pair, SushiDish dish);

    void finishDinner();

    class ObliviousPhilosopher extends Thread implements Philosopher {

        protected ChopsticksPair chopsticks;
        private SushiDish sushiDish;

        protected final Random rps = new Random();

        public ObliviousPhilosopher(String name) {
            super(name);
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

            try {
                int eaten = 0;
                while (sushiDish.hasSushi()) {
                    if (tryEatSushi()) {
                        eaten++;
                    } else {
                        System.out.print(this.getName() + ": not my turn! Will check a bit later... ");
                    }
                    Thread.sleep(rps.nextInt(10, 100));
                }
                Thread.sleep(100);
                System.out.println(this.getName() + ": ate " + eaten + " pieces of sushi. Perfect, I'm so sated!");
            } catch (Exception e) {
                System.out.println(this.getName() + ": something went wrong! Finishing eating...");
            }
        }

        protected boolean tryEatSushi() {
            if (chopsticks.take()) {
                eatSushi();
                chopsticks.release();
                return true;
            }
            return false;
        }

        protected void eatSushi() {
            int sushiRemaining = sushiDish.eatSushi();
            if (sushiRemaining < 10) {
                if (getName().equals("Barron")) {
                    throw new RuntimeException("Sushi is running out, panic!!!");
                }
            }
            System.out.println(this.getName() + ": took a piece! Sushi remaining: " + sushiRemaining);
        }
    }

    class CarefulPhilosopher extends ObliviousPhilosopher {

        public CarefulPhilosopher(String name) {
            super(name);
        }

        @Override
        protected boolean tryEatSushi() {
            if (chopsticks.take()) {
                try {
                    eatSushi();
                    return true;
                } finally {
                    chopsticks.release();
                }
            }
            return false;
        }
    }
}