package yevhent.demo.multithreading.bases;

public class ThreadLifecycle {

    public static void main(String[] args) {

        // NEW
        System.out.println("1. Barron takes Sausage and invites Olivia to join.");
        Sausage sausage = new Sausage();
        Thread olivia = new ChefOlivia(sausage);
        System.out.println("   Olivia state: " + olivia.getState() + time());

        // RUNNABLE
        System.out.println();
        System.out.println("2. Barron weighs Sausage & asks Olivia to start.");
        sausage.start();
        holdOn(1000); // to let Sausage start and be caught by Olivia at calculation state
        olivia.start();
        System.out.println("   Olivia state: " + olivia.getState() + time());
        System.out.println();

        // BLOCKED
        holdOn(1000); // to catch BLOCKED state in a middle (in fact 300 + 1000)
        System.out.println();
        System.out.println("3. Barron continues cooking soup. Olivia is waiting for Sausage calculations.");
        System.out.println("   Olivia state: " + olivia.getState() + time());
        System.out.println();

        // TIME_WAITING
        holdOn(2500); // to catch new state in a middle
        System.out.println("4. Barron continues cooking soup. Olivia is waiting for sausage to thaw.");
        System.out.println("   Olivia state: " + olivia.getState() + time());
        System.out.println();

        // WAITING
        holdOn(3000);
        System.out.println();
        System.out.println("5. Barron continues cooking soup. Olivia is cutting sausage.");
        System.out.println("   Olivia state: " + olivia.getState() + time());
        System.out.println();

        // TERMINATED
        System.out.println();
        System.out.println("6. Barron patiently waits for Olivia to finish and join...");
        holdOn(olivia);
        System.out.println("   Olivia state: " + olivia.getState() + time());

        System.out.println();
        System.out.println("Barron and Olivia are both done!");
    }

    static String time() {
        long currentTime = System.currentTimeMillis();
        long relativeTime = currentTime - startTime;
        return " " + relativeTime;
        //return String.format(" %2.2f", ((double) relativeTime) / 1000);
    }

    static void holdOn(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void holdOn(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static long startTime = System.currentTimeMillis();
}

class ChefOlivia extends Thread {

    private final Sausage sausage;

    public ChefOlivia(Sausage sausage) {
        this.sausage = sausage;
    }

    public void run() {
        System.out.println("Olivia: I'm reading sausage weigh ..." + ThreadLifecycle.time());
        sausage.weigh(100);

        System.out.println("Olivia: I'm waiting for sausage to thaw ..." + ThreadLifecycle.time());
        ThreadLifecycle.holdOn(sausage);

        System.out.println("Olivia: I'm cutting sausage ..." + ThreadLifecycle.time());
        ThreadLifecycle.holdOn(3000);

        System.out.println("Olivia: I'm done cutting sausage." + ThreadLifecycle.time());
    }
}

class Sausage extends Thread {

    public void run() {
        System.out.println("Sausage: I'm calculating my weigh ..." + ThreadLifecycle.time());
        weigh(3000);
        System.out.println("Sausage: I calculated my weigh and started thawing ..." + ThreadLifecycle.time());
        ThreadLifecycle.holdOn(3000);
        System.out.println("Sausage: I'm thawed." + ThreadLifecycle.time());
    }

    synchronized int weigh(long hold) {
        ThreadLifecycle.holdOn(hold);
        return 123;
    }
}