package research.multithreading;

public class SynchronizedTest {

    static int result = -1;

    public static void main(String[] args) throws InterruptedException {

        SynchronizedTest test = new SynchronizedTest();

        Thread thread1 = start(()-> {
            System.out.println("increase start");
            test.increase();
            System.out.println("increase end");
        });
        Thread thread2 = start(()-> {
            System.out.println("get start");
            result = test.get();
            System.out.println("get end ");
        });
        thread1.join();
        thread2.join();
        System.out.println("Synchronized is " + (result == 1));
    }

    static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static Thread start(Runnable task){
        Thread thread = new Thread(task);
        thread.start();
        return thread;
    }

    private Integer count = 0;

    synchronized int get(){
        sleep(5);
        System.out.println("get() "+ count);
        return count;
    }

    synchronized void increase() {
        sleep(10);
        count++;
        System.out.println("increase() "+ count);
    }
}