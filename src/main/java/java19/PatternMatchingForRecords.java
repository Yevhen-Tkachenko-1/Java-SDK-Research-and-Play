package java19;

public class PatternMatchingForRecords {

    record Point(int x, int y) {}

    public static void main(String[] args) {
        Object obj = new Point(1, 2);

        if(obj instanceof Point(int x, int y)){
            System.out.println(x + y);
        }

        System.out.println(switch (obj){
            case Point(int x, int y) -> x + y;
            default -> throw new IllegalStateException("Unexpected value: " + obj);
        });
    }
}
