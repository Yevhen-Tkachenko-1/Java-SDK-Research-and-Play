package yevhent.demo.multithreading.concurrency.locks.nested;

public class NestedReentrantLockDemo {

    private static final int number = 10_000_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        System.out.println("Yevhen: Hi guys!");
        System.out.println("        Olivia, please buy " + number + " potatoes and " + number + " garlic in total.");
        System.out.println("        Barron, please buy " + number + " potatoes.");
        System.out.println("        So, in total we should have " + number * 2 + " potatoes, " + number + " garlic.");
        System.out.println();
        goShopping(new Basket.SimpleBasket());
        System.out.println();
        System.out.println("Yevhen: Sorry guys, but we have more potatoes than needed!");
        System.out.println("        This time, let's have Blocking Reentrant basket:");
        System.out.println();
        Basket.NestedReentrantBasket basket = new Basket.NestedReentrantBasket();
        goShopping(basket);
        System.out.println();
        System.out.println("Yevhen: Great! We have right amount of potatoes now!");
        System.out.println("        Nested lock was reached " + basket.nestedLocks + " times.");
        System.out.println();
    }

    static void goShopping(Basket basket) throws InterruptedException {
        Thread barron = new Barron(basket);
        Thread olivia = new Olivia(basket);
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("Barron and Olivia: We bought "
                + basket.getPotatoTotal() + " potatoes, "
                + basket.getGarlicTotal() + " garlic.");
    }

    static class Barron extends Thread {

        private final Basket basket;

        public Barron(Basket basket) {
            this.basket = basket;
        }

        public void run() {
            System.out.println("Barron: I'm going to add " + number + " potatoes ...");
            for (int i = 0; i < number; i++) {
                basket.putOnePotato();
            }
        }
    }

    static class Olivia extends Thread {

        private final Basket basket;

        public Olivia(Basket basket) {
            this.basket = basket;
        }

        public void run() {
            System.out.println("Olivia: I'm going to add " + number + " potatoes and " + number + " garlic ...");
            for (int i = 0; i < number; i++) {
                basket.putOneGarlicAndOnePotato();
            }
        }
    }
}