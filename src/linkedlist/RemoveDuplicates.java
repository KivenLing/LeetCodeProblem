package linkedlist;

import util.ListNode;
/*
ID: 82, 83
 */
public class RemoveDuplicates {
    /*
    ID : 83
    Given a sorted linked list, delete all duplicates
    such that each element appear only once.

    For example,
    Given 1->1->2, return 1->2.
    Given 1->1->2->3->3, return 1->2->3.
    */

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

    /*
    ID : 82
    Given a sorted linked list, delete all nodes that have duplicate
    numbers, leaving only distinct numbers from the original list.

    For example,
    Given 1->2->3->3->4->4->5, return 1->2->5.
    Given 1->1->1->2->3, return 2->3.
     */
    public static ListNode deleteDuplicatesII(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode cur = head;
        while (cur != null){
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                //cur 指向 相同元素最后一个
                cur = cur.next;
                pre.next = cur;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }
}
