package yevhent.demo.multithreading.concurrency.locks.nonblocking;

public class NonBlockingLockDemo {

    private static final int number = 15;

    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        System.out.println("Yevhen: Hi guys!");
        System.out.println("        Olivia, please check if we have " + number + " potatoes and buy " + number + " garlic in total.");
        System.out.println("        Barron, please check if we have " + number + " potatoes and buy " + number + " carrots in total.");
        System.out.println("        So, in total we should have " + number * 2 + " potatoes, " + number + " garlic, " + number + " carrots.");
        System.out.println();
        goShopping(new Basket.SimpleBasket());
        System.out.println();
        System.out.println("Yevhen: Sorry guys, but we have less potatoes than needed!");
        System.out.println("        This time, let's have Blocking Reentrant basket:");
        System.out.println();
        goShopping(new Basket.BlockingReentrantBasket());
        System.out.println();
        System.out.println("Yevhen: That's better! We have right amount of potatoes now!");
        System.out.println("        However, it took more time now.");
        System.out.println("        Guys, you can ignore potatoes if there are a lot of people in the line.");
        System.out.println("        This time, let's have Non-Blocking Reentrant basket:");
        System.out.println();
        goShopping(new Basket.NonBlockingReentrantBasket());
        System.out.println();
        System.out.println("Yevhen: Great job! You've done it faster now!");
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
                + basket.getGarlicTotal() + " garlic, "
                + basket.getCarrotTotal() + " carrots.");
    }

    static class Olivia extends Thread {

        private final Basket basket;

        public Olivia(Basket basket) {
            this.basket = basket;
        }

        public void run() {
            long start = System.currentTimeMillis();
            System.out.println("Olivia: I'm going to check " + number + " potatoes and add " + number + " garlic ...");
            basket.buyPotatoAndGarlic(number);
            System.out.println("Olivia: I'm done, it took me " + (System.currentTimeMillis() - start));
        }
    }

    static class Barron extends Thread {

        private final Basket basket;

        public Barron(Basket basket) {
            this.basket = basket;
        }

        public void run() {
            long start = System.currentTimeMillis();
            System.out.println("Barron: I'm going to check " + number + " potatoes and add " + number + " carrots ...");
            basket.buyPotatoAndCarrot(number);
            System.out.println("Barron: I'm done, it took me " + (System.currentTimeMillis() - start));
        }
    }
}