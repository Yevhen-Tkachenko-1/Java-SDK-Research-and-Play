package yevhent.demo.multithreading.bases;

public class ThreadVsProcess {
    public static void main(String[] args) {

        // display current information about this process
        Runtime rt = Runtime.getRuntime();
        printInfo(rt);
        System.out.println();
        System.out.println("Starting 6 CPUWaster threads...");
        System.out.println();
        for (int i = 0; i < 6; i++) {
            new CPUWaster().start();
        }
        printInfo(rt);
    }

    static void printInfo(Runtime rt) {
        long usedKB = (rt.totalMemory() - rt.freeMemory()) / 1024;
        System.out.format("  Process ID: %d\n", ProcessHandle.current().pid());
        System.out.format("Thread Count: %d\n", Thread.activeCount());
        System.out.format("Memory Usage: %d KB\n", usedKB);
    }
}

class CPUWaster extends Thread {
    public void run() {
        while (true) {
        }
    }
}