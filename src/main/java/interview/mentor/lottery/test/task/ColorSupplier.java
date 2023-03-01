package interview.mentor.lottery.test.task;

import java.util.Random;

public class ColorSupplier {


    public static String getRandomColor() {
        int index = new Random().nextInt(Colors.values().length);
        return Colors.values()[index].toString();
    }
}
