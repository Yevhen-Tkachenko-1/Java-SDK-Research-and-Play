package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoSumTest {

    @Test
    void twoSum() {

        TwoSum tested = new TwoSum();

        assertArrayEquals(new int[]{0, 1}, tested.twoSum2(new int[]{2, 7, 11, 15}, 9));
        assertArrayEquals(new int[]{1, 2}, tested.twoSum2(new int[]{3, 2, 4}, 6));
        assertArrayEquals(new int[]{0, 1}, tested.twoSum2(new int[]{3, 3}, 6));
    }

}