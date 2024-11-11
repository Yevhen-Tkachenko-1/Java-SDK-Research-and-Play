package yevhent.demo.multithreading.concurrency.datarace;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

interface Basket {

    /**
     * Thread safe
     */
    void putOneGarlic();

    /**
     * Not Thread safe, just to check amount
     */
    int getTotal();

    class SimpleBasket implements Basket {

        private int garlicCount = 0;

        @Override
        public void putOneGarlic() {
            garlicCount++;
        }

        @Override
        public int getTotal() {
            return garlicCount;
        }
    }

    class ReentrantBasket extends SimpleBasket {

        private final Lock lock = new ReentrantLock();

        @Override
        public void putOneGarlic() {
            lock.lock();
            super.putOneGarlic();
            lock.unlock();
        }
    }

    class MethodSynchronizedBasket extends SimpleBasket {

        @Override
        public synchronized void putOneGarlic() {
            super.putOneGarlic();
        }
    }

    class BlockSynchronizedBasket extends SimpleBasket {

        @Override
        public void putOneGarlic() {
            synchronized (this) {
                super.putOneGarlic();
            }
        }
    }

    class AtomicBasket implements Basket {

        private final AtomicInteger garlicCount = new AtomicInteger(0);

        @Override
        public void putOneGarlic() {
            garlicCount.incrementAndGet();
        }

        @Override
        public int getTotal() {
            return garlicCount.get();
        }
    }
}