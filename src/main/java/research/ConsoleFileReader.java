package research;

import java.io.File;
import java.util.Arrays;

public class ConsoleFileReader {


    public static void main(String[] args) {

        File[] files = new File("").listFiles();

        System.out.println(Arrays.asList(files));
    }



}
