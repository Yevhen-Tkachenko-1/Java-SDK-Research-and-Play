package leetcode;

import java.util.Objects;

public class AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            String val = String.valueOf(this.val);
            if(next != null){
                val += next;
            }
            return val;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == null){
                return false;
            }
            if(this.val != ((ListNode)obj).val){
                return false;
            }
            return Objects.equals(this.next, ((ListNode)obj).next);
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode first = l1;
        ListNode second = l2;
        ListNode result = new ListNode(l1.val + l2.val);
        ListNode sum = result;
        while (true) {
            if (first.next == null && second.next == null) {
                break;
            }
            if (first.next == null) {
                first.next = new ListNode(0);
            }
            if (second.next == null) {
                second.next = new ListNode(0);
            }
            int nextSumValue = first.next.val + second.next.val;
            if (sum.val > 9) {
                nextSumValue++;
                sum.val -= 10;
            }
            sum.next = new ListNode(nextSumValue);
            first = first.next;
            second = second.next;
            sum = sum.next;
        }
        if (sum.val > 9) {
            sum.val -= 10;
            sum.next = new ListNode(1);
        }
        return result;
    }
}
