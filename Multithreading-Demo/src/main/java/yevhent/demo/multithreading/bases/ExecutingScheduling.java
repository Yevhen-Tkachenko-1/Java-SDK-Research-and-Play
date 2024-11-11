package yevhent.demo.multithreading.bases;

public class ExecutingScheduling {

    public static void main(String []args) throws InterruptedException {
        int barronMore = 0;
        int oliviaMore = 0;

        for (int i = 0; i <= 10; i++) {
            if (run(i)) {
                barronMore++;
            } else {
                oliviaMore++;
            }
        }
        System.out.println();
        System.out.println("Barron chopped more vegetables " + barronMore + " times");
        System.out.println("Olivia chopped more vegetables " + oliviaMore + " times");
    }

    static boolean run(int round) throws InterruptedException {
        System.out.println("Let's chop! Round number " + round);
        VegetableChopper.chopping = true;

        VegetableChopper barron = new VegetableChopper("Barron" + "-" + round);
        VegetableChopper olivia = new VegetableChopper("Olivia" + "-" + round);

        // Barron start chopping
        barron.start();
        // Olivia start chopping
        olivia.start();
        // continue chopping for 1 second
        Thread.sleep(100);
        // stop chopping
        VegetableChopper.chopping = false;

        barron.join();
        olivia.join();
        System.out.println();
        System.out.format("Barron chopped %d vegetables.\n", barron.vegetableCount);
        System.out.format("Olivia chopped %d vegetables.\n", olivia.vegetableCount);
        return barron.vegetableCount > olivia.vegetableCount;
    }
}

class VegetableChopper extends Thread {

    public int vegetableCount = 0;
    public static boolean chopping = true;

    public VegetableChopper(String name) {
        this.setName(name);
    }

    public void run() {
        System.out.println(this.getName() + " started chop a vegetable!");
        while (chopping) {
            System.out.print(this.getName().startsWith("Barron") ? "+B" : "+O");
            vegetableCount++;
        }
    }
}


