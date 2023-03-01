package interview;

public class ReverseTask
{

    public static void main(String[] args) {
        System.out.println("Hello");

        System.out.println(reverse("12345"));
        System.out.println(reverse("123456"));

    }

    public static String reverse(String s) { // 12345 -> 21435

        byte[] bytes = s.getBytes();

        for (int i = 0; i < (bytes.length - 1); i += 2) {

            byte b = bytes[i];
            bytes[i] = bytes[i + 1];
            bytes[i + 1] = b;
        }
        return new String(bytes);
    }
}
