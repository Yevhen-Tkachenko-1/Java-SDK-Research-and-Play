package java12;

public class PatternMatchingInstanceofVariable {

    public static void main(String[] args) {

        // previous
        Object obj1 = "Hello World!";
        if (obj1 instanceof String) {
            String s = (String) obj1;
            int length = s.length();
            System.out.println(length);
        }

        // new
        Object obj2 = "Hello Yevhen!";
        if(obj2 instanceof String greeting){
            int length = greeting.length();
            System.out.println(length);
        }
    }
}
