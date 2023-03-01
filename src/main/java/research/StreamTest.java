package research;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
        String value = new ArrayList<String>().stream().collect(Collectors.joining());
        System.out.println(value.isEmpty());
        System.out.println("["+value+"]");
    }
}
