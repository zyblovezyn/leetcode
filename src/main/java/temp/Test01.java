package temp;

import java.util.HashMap;

/**
 * @author Miles
 * @version 1.0.0
 * @Description TODO
 * <p>
 * @ClassName Test01.java
 * @createTime 2021-01-15 16:44:00
 */
public class Test01 {
    public static void main(String[] args) {
//        twoSum(new int[]{2, 7}, 9);

        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);


        l4.next = l5;
        l5.next = l6;


        addTwoNumbers(l1, l4);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean exceed10 = false;
        ListNode head1 = l1;
        ListNode head2 = l2;
        ListNode head = new ListNode();
        ListNode headTemp1 = head;
        ListNode headTemp2 = head;
        ListNode headTemp3 = head;
        while (head1 != null) {
            if (headTemp1.next == null) {
                headTemp1.next = new ListNode();
            }
            headTemp1.val = head1.val;
            head1 = head1.next;
            headTemp1 = headTemp1.next;
        }

        while (head2 != null) {
            if (headTemp2.next == null) {
                headTemp2.next = new ListNode();
            }
            headTemp2.val = head2.val + headTemp2.val;
            head2 = head2.next;
            headTemp2 = headTemp2.next;
        }
        while (headTemp3.next != null) {
            if (exceed10) {
                headTemp3.val += 1;
            }
            if (headTemp3.val > 9) {
                headTemp3.val = headTemp3.val - 10;
                exceed10 = true;
            } else {
                exceed10 = false;
            }
            headTemp3 = headTemp3.next;
        }
        if (exceed10) {
            headTemp3.val += 1;
            if (headTemp3.val >= 10) {
                headTemp3.val -= 10;
                headTemp3.next = new ListNode(1);
            }
        }

        ListNode headTemp4 = head;
        while (headTemp4.next != null && headTemp4.next.next != null) {
            headTemp4 = headTemp4.next;
        }
        if (headTemp4.next.val <= 0) {
            headTemp4.next = null;
        }
        return head;
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = target - nums[i];
            if (map.keySet().contains(key)) {
                return new int[]{map.get(target - key), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}


class ListNode {
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
}


