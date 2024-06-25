package interview;

import java.util.Arrays;

public class IntegerRandomShuffler {

    /**
     * Task:
     * <br>Return randomly shuffled integer numbers based on parameter N which is total number of Integers in range [1, N]
     * <br>
     * <br>For example:
     * <br>randomShuffle(5) >> [3, 5, 2, 4, 1]
     * <br>randomShuffle(5) >> [4, 2, 3, 1, 5]
     */
    public static void main(String[] args) {
        System.out.println("Hello world!");

        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(randomShuffle(5)));
        }

        for (int i = 0; i < 15; i++) {
            System.out.println(Arrays.toString(randomShuffle(15)));
        }
    }

    /**
     * Solution:
     * <br>1. Create and populate array of integers in numeric order from 1 to N.
     * <br>2. Go cell by cell starting from array[0]
     * <br>3. For each cell we set value from all available cells.
     * (for current cell with index i, we have numbers available from array[i] to array[N])
     * <br>4. To get random number we find random index in range [i, N)
     * <br>5. Swap number for current index with number for random index.
     * They are current cell and some cell on the right (or the same cell).
     * <br>6. Repeat till index i = N-1 (we don't need the last step)
     * <br><br>
     * Example for 5:
     * <br>
     * <br>Step 1
     * <br>input = [1, 2, 3, 4, 5]
     * <br>currentIndex = 0, randomIndex = 3, number 4
     * <br>swap 1 and 4
     * <br>result = [4, 2, 3, 1, 5]
     * <br>
     * <br>Step 2
     * <br>input = [4, 2, 3, 1, 5]
     * <br>currentIndex = 1, randomIndex = 4, number 5
     * <br>swap 2 and 5
     * <br>result = [4, 5, 3, 1, 2]
     * <br>
     * <br>Step 3
     * <br>input = [4, 5, 3, 1, 2]
     * <br>currentIndex = 2, randomIndex = 2, number 3
     * <br>swap 3 and 3
     * <br>result = [4, 5, 3, 1, 2]
     * <br>
     * <br>Step 4
     * <br>input = [4, 5, 3, 1, 2]
     * <br>currentIndex = 3, randomIndex = 5, number 2
     * <br>swap 1 and 2
     * <br>result = [4, 5, 3, 2, 1]
     * <br>
     * <br>Done!
     */
    static int[] randomShuffle(int n) {

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = i + 1;
        }

        for (int i = 0; i < n - 1; i++) {
            int randomIndex = randomIndex(i, n);
            int randomNumber = result[randomIndex];
            result[randomIndex] = result[i];
            result[i] = randomNumber;
        }
        return result;
    }

    static int randomIndex(int lower, int n) {
        double random = Math.random();
        return (int) (lower * (1.0 - random) + n * random);
    }

}
