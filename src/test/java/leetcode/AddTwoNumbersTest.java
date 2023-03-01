package leetcode;

import leetcode.AddTwoNumbers.ListNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddTwoNumbersTest {

    @Test
    void addTwoNumbers() {

        AddTwoNumbers tested = new AddTwoNumbers();

        assertEquals(listNode(8,9,9,9,0,0,0,1), tested.addTwoNumbers(listNode(9,9,9,9,9,9,9), listNode(9,9,9,9)));
    }

    ListNode listNode(int... nums) {
        ListNode listNode = new ListNode(nums[0]);
        ListNode temp = listNode;
        for (int i = 1; i < nums.length; i++) {
            temp.next = new ListNode(nums[i]);
            temp = temp.next;
        }
        return listNode;
    }
}