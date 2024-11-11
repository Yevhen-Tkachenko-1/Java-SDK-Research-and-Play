package yevhent.demo.multithreading.concurrency.datarace;

public class MutualExclusionDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println();
        System.out.println("Guys, let's buy 20_000_000 garlic in total!");
        System.out.println();
        goShopping(new Basket.SimpleBasket());
        System.out.println();
        System.out.println("Sorry guys, but this is not what was expected!");
        System.out.println();
        System.out.println("This time, let's have Reentrant basket:");
        goShopping(new Basket.ReentrantBasket());
        System.out.println();
        System.out.println("Or you can try Method Synchronized basket:");
        goShopping(new Basket.MethodSynchronizedBasket());
        System.out.println();
        System.out.println("Alternatively, we have Block Synchronized basket:");
        goShopping(new Basket.BlockSynchronizedBasket());
        System.out.println();
        System.out.println("Finally, try Atomic basket:");
        goShopping(new Basket.AtomicBasket());
        System.out.println();
        System.out.println("Great! Now we are good.");
    }

    static void goShopping(Basket basket) throws InterruptedException {
        Thread barron = new Shopper("Barron", basket);
        Thread olivia = new Shopper("Olivia", basket);
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We bought " + basket.getTotal() + " garlic.");
    }

    static class Shopper extends Thread {

        private final Basket basket;

        public Shopper(String name, Basket basket) {
            super(name);
            this.basket = basket;
        }

        public void run() {
            System.out.println(this.getName() + ": I'm going to add 10_000_000 garlic ...");

            for (int i = 0; i < 10_000_000; i++)
                basket.putOneGarlic();
        }
    }
}