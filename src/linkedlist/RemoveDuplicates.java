package linkedlist;

import util.ListNode;
/*
ID : 83
Given a sorted linked list, delete all duplicates
such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
 */
public class RemoveDuplicates {
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null){
            while (cur.next != null && cur.val == cur.next.val){
                cur.next = cur.next.next;
            }
            //cur.next == null || cur.val != cur.next.val
            cur = cur.next;
        }
        return head;
    }
}
