package linkedlist;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import util.LinkedListHelper;
import util.ListNode;

public class DeleteNode {

    /*
    ID : 237
    Write a function to delete a node (except the tail) in a singly
    linked list, given only access to that node.

    Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given
    the third node with value 3, the linked list should become
    1 -> 2 -> 4 after calling your function.
     */
    public void deleteNode(ListNode node){
        if(node == null || node.next == null)
            throw new IllegalArgumentException("node should be valid and can not be the tail node.");

        node.val = node.next.val;
        node.next = node.next.next;
    }


    /*
    ID : 19
    Given a linked list, remove the nth node from the end of list
    and return its head.

    For example,

    Given linked list: 1->2->3->4->5, and n = 2.

    After removing the second node from the end, the linked list
    becomes 1->2->3->5.
    Note:
    Given n will always be valid.
    Try to do this in one pass.
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        ListNode tail = LinkedListHelper.getnthNodeOfList(head, n + 1);
        while (tail != null){
            pre = pre.next;
            cur = cur.next;
            tail = tail.next;
        }
        //tail == null
        pre.next = cur.next;
        return dummy.next;
    }

}
