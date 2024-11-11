package yevhent.demo.multithreading.concurrency.locks.nested;

import java.util.concurrent.locks.ReentrantLock;

public interface Basket {

    /**
     * Should be Thread safe. Potentially has nested lock
     */
    void putOneGarlicAndOnePotato();

    /**
     * Should be Thread safe. Potentially has nested lock
     */
    void putOnePotato();

    /**
     * Not Thread safe, just to check amount
     */
    int getGarlicTotal();

    /**
     * Not Thread safe, just to check amount
     */
    int getPotatoTotal();

    class SimpleBasket implements Basket {

        protected int garlicCount = 0;
        protected int potatoCount = 0;

        @Override
        public void putOneGarlicAndOnePotato() {
            garlicCount++;
            putOnePotato();
        }

        @Override
        public void putOnePotato() {
            potatoCount++;
        }

        @Override
        public int getGarlicTotal() {
            return garlicCount;
        }

        @Override
        public int getPotatoTotal() {
            return potatoCount;
        }
    }

    class NestedReentrantBasket extends SimpleBasket {

        protected final ReentrantLock lock = new ReentrantLock();

        public int nestedLocks = 0;

        @Override
        public void putOneGarlicAndOnePotato() {
            try {
                lock.lock();
                super.putOneGarlicAndOnePotato();
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void putOnePotato() {
            try {
                lock.lock();
                super.putOnePotato();
                if (lock.getHoldCount() == 2) {
                    nestedLocks++;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}