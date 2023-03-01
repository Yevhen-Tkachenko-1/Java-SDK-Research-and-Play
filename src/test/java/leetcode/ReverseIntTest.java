package leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseIntTest {

    @Test
    void reverse() {

        ReverseInt tested = new ReverseInt();
        assertEquals(321, tested.reverse(123));
        assertEquals(-321, tested.reverse(-123));
        assertEquals(21, tested.reverse(120));
        assertEquals(0, tested.reverse(Integer.MIN_VALUE));
        assertEquals(0, tested.reverse(Integer.MAX_VALUE));
    }
}