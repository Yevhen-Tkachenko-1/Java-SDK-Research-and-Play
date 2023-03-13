package java19;

public class RecordsTest {

    record Point(int x, int y) {}

    public static void main(String[] args) {
        Point point = new Point(1, 2);
        previousImpl(point);
        newImpl(point);
    }

    static void previousImpl(Object o) {
        if (o instanceof Point p) {
            int x = p.x();
            int y = p.y();
            System.out.println(x + y);
        }
    }

    static void newImpl(Object o) {
        if (o instanceof Point(int x, int y)) {
            System.out.println(x + y);
        }
    }
}
