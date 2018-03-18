package linkedlist;

import util.ListNode;

/*
ID :24
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify
the values in the list, only nodes itself can be changed.
 */
public class SwapNodes {
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode cur = head;
        ListNode pre = dummyHead;
        while (cur != null && cur.next != null){
            ListNode nextNode = cur.next;
            pre.next = nextNode;
            cur.next = nextNode.next;
            nextNode.next = cur;
            pre = cur;
            cur = cur.next;
        }
        return dummyHead.next;
    }
}
