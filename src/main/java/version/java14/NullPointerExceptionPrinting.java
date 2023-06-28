package version.java14;

public class NullPointerExceptionPrinting {

    public static void main(String[] args) {

        int[] arr = null;

        arr[0] = 1; // Cannot store to int array because "arr" is null

    }
}
