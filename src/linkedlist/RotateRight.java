package linkedlist;

import util.LinkedListHelper;
import util.ListNode;

public class RotateRight {
    /*
    ID : 61
    Given a list, rotate the list to the right by k places, where k is
    non-negative.


    Example:

    Given 1->2->3->4->5->NULL and k = 2,

    return 4->5->1->2->3->NULL.
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;
        int size = LinkedListHelper.sizeOfNode(head);
        k = k % size;
        if (k == 0)
            return head;
        int tailPos = size - k;
        ListNode tail = LinkedListHelper.getnthNodeOfList(head, tailPos);
        ListNode dummy = tail.next;
        ListNode lastNode = LinkedListHelper.getLastNode(dummy);
        tail.next = null;
        lastNode.next = head;
        return dummy;
    }
}
