package yevhent.demo.multithreading.concurrency.locks.nonblocking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public interface Basket {

    /**
     * Should be Thread safe.
     */
    void buyPotatoAndGarlic(int amount);

    /**
     * Should be Thread safe.
     */
    void buyPotatoAndCarrot(int amount);

    /**
     * Not Thread safe, just to check amount
     */
    int getGarlicTotal();

    /**
     * Not Thread safe, just to check amount
     */
    int getPotatoTotal();

    /**
     * Not Thread safe, just to check amount
     */
    int getCarrotTotal();

    class SimpleBasket implements Basket {

        protected int garlicCount = 0;
        protected int potatoCount = 0;
        protected int carrotCount = 0;

        @Override
        public void buyPotatoAndGarlic(int amount) {
            for (int i = 0; i < amount; i++) {
                putOneGarlic();
                putPotato(1);
            }
        }

        @Override
        public void buyPotatoAndCarrot(int amount) {
            for (int i = 0; i < amount; i++) {
                putOneCarrot();
                putPotato(1);
            }
        }

        protected void putOneGarlic() {
            standInLine();
            garlicCount++;
        }

        protected void putOneCarrot() {
            standInLine();
            carrotCount++;
        }

        protected void putPotato(int amount) {
            int potatoResult = potatoCount + amount;
            standInLine();
            potatoCount = potatoResult;
        }

        @Override
        public int getGarlicTotal() {
            return garlicCount;
        }

        @Override
        public int getPotatoTotal() {
            return potatoCount;
        }

        @Override
        public int getCarrotTotal() {
            return carrotCount;
        }

        protected void standInLine() {
            try {
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class BlockingReentrantBasket extends SimpleBasket {

        protected final Lock lock = new ReentrantLock();

        @Override
        protected void putPotato(int amount) {
            try {
                lock.lock();
                super.putPotato(amount);
            } finally {
                lock.unlock();
            }
        }
    }

    class NonBlockingReentrantBasket extends SimpleBasket {

        protected final Lock lock = new ReentrantLock();

        @Override
        public void buyPotatoAndGarlic(int amount) {
            buyPotatoAndOtherProducts(amount, this::putOneGarlic);
        }

        @Override
        public void buyPotatoAndCarrot(int amount) {
            buyPotatoAndOtherProducts(amount, this::putOneCarrot);
        }

        private void buyPotatoAndOtherProducts(int amount, Runnable otherProduct) {
            int potato = 0;
            for (int i = 0; i < amount; i++) {
                otherProduct.run();
                potato++;
                if (lock.tryLock()) {
                    try {
                        putPotato(potato);
                        potato = 0;
                    } finally {
                        lock.unlock();
                    }
                }
            }
            if (potato > 0) {
                try {
                    lock.lock();
                    putPotato(potato);
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}