package java19;

public class WhenSwitchExpression {

    public static void main(String[] args) {

        System.out.println(checkShape(new Circle()));
        System.out.println(checkShape(new Triangle()));
    }

    static String checkShape(Shape shape) {
        return switch (shape) {
            case Triangle t when (t.getNumberOfSides() != 3) -> "This is a weird triangle";
            case Circle c when (c.getNumberOfSides() != 0) -> "This is a weird circle";
            default -> "Just a normal shape";
        };
    }

    interface Shape{
        int getNumberOfSides();
    }

    static class Circle implements Shape{
        @Override
        public int getNumberOfSides() {
            return 0;
        }
    }
    static class Triangle implements Shape{
        @Override
        public int getNumberOfSides() {
            return 0;
        }
    }
}
