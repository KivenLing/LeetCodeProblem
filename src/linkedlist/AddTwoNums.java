package linkedlist;

import util.ListNode;

import java.util.Stack;

//两个相近的问题 ID:2, ID:445
public class AddTwoNums {
    /*
    ID:2
    You are given two non-empty linked lists representing
    two non-negative integers. The digits are stored in
    reverse order and each of their nodes contain a single
    digit. Add the two numbers and return it as a linked list.

    You may assume the two numbers do not contain any
    leading zero, except the number 0 itself.

    Example

    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8
    Explanation: 342 + 465 = 807.
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode tempAns = new ListNode(0);
        tempAns.next = ans;
        int add = 0;//进位
        while (l1 != null || l2 != null){
            tempAns = tempAns.next;
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = x + y + add;
            tempAns.val = sum % 10;
            add = sum / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            tempAns.next = new ListNode(0);
        }
        tempAns.next.val += add;
        if (tempAns.next.val <= 0)
            tempAns.next = null;
        return ans;
    }

    /*
    ID:445
    You are given two non-empty linked lists representing two
    non-negative integers. The most significant digit comes
    first and each of their nodes contain a single digit.
    Add the two numbers and return it as a linked list.

    You may assume the two numbers do not contain any leading zero,
    except the number 0 itself.

    Follow up:
    What if you cannot modify the input lists? In other words, reversing
    the lists is not allowed.

    Example:

    Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 8 -> 0 -> 7
     */
    public static ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode ans = null;
        int add = 0;
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        Stack<Integer> ansStack = new Stack<>();
        while (l1 != null){
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            s2.push(l2.val);
            l2 = l2.next;
        }
        while (!s1.isEmpty() || !s2.isEmpty() || add != 0){
            int x = (s1.isEmpty()) ? 0 : s1.pop();
            int y = (s2.isEmpty()) ? 0 : s2.pop();
            int sum = x + y + add;
            ListNode cur = new ListNode(sum % 10);
            add = sum / 10;
            cur.next = ans;
            ans = cur;
        }
        return ans;
    }
    public static void main(String[] args){
        ListNode l1 = new ListNode(new int[]{2, 4, 3});
        ListNode l2 = new ListNode(new int[]{5, 6, 4});
        ListNode ans = addTwoNumbers(l1, l2);
        System.out.println(ans);
    }
}
