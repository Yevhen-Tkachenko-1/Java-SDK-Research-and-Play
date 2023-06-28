package version.java17;

public class PatternMatchingSwitchExpression {

    public static void main(String[] args) {

        System.out.println(checkObject(new Circle()));
        System.out.println(checkObject(new Human("Yevhen", 27, "SE")));
        System.out.println(checkObject(null));
        System.out.println(checkObject("test"));

        System.out.println(checkShape(new Circle()));
        System.out.println(checkShape(new Triangle()));
    }


    static String checkObject(Object obj) {
        return switch (obj) {
            case Human h -> "Name: %s, age: %s and profession: %s".formatted(h.name(), h.age(), h.profession());
            case Circle c -> "This is a circle";
            case Shape s -> "It is just a shape";
            case null -> "It is null";
            default -> "It is an object";
        };
    }

    static String checkShape(Shape shape) {
        return switch (shape) {
            // Old patterns from JEP 406 are not available since Java 19 preview
            //case Triangle t && (t.getNumberOfSides() != 3) -> "This is a weird triangle";
            //case Circle c && (c.getNumberOfSides() != 0) -> "This is a weird circle";
            default -> "Just a normal shape";
        };
    }

    record Human (String name, int age, String profession) {}

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
