package version.java12;

public class StringMethods {

    public static void main(String[] args) {

        String text = "Hello Yevhen!\nThis is Java 12 article.";

        text = text.indent(4);
        System.out.println(text);

        text = text.indent(-10);
        System.out.println(text);

        System.out.println();
        String s = "100";
        Integer transformed = s.transform(Integer::parseInt);
        System.out.println(transformed);
    }
}
