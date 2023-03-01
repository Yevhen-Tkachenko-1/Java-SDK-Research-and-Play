package research.math;

import java.util.Random;

public class RandomTest {

    public static void main(String[] args) {

        Random random = new Random();
        random.nextInt(1);
        System.out.println(Color.size);
        System.out.println(Color.black.name());
    }

    public enum Color{
        black("Black"),
        white("Black"),
        red("Black"),
        blue("Black");


        private final String name;
        Color(String name){
            this.name = name;
        }

//        public String getName() {
//            return name;
//        }

        public static final int size;
        private static final Color[] values;
        static {
            size = values().length;
            values = values();
        }
        static Color getValue(int i){
            return values[i];
        }
    }
}
