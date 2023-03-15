package java14;

public class TextBlocks {

    public static void main(String[] args) {
        String singleLine = """
                A quick brown fox jumps over a lazy dog; \
                the lazy dog howls loudly.\s\
                The other sentence.
                """;

        System.out.println(singleLine);
    }
}
