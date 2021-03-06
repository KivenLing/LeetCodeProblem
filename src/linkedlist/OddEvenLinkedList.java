package linkedlist;

import util.ListNode;

/*
ID:328
Given a singly linked list, group all odd nodes
together followed by the even nodes. Please note
here we are talking about the node number and not
the value in the nodes.

You should try to do it in place. The program should
run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
 */
public class OddEvenLinkedList {

    public static ListNode oddEvenList(ListNode head) {
        //节点数少于3
        if (head == null || head.next == null || head.next.next == null)
            return head;
        ListNode curOdd = head.next.next;
        ListNode lastOdd = head;
        ListNode preEven = head.next;
        while (curOdd != null){
            preEven.next = curOdd.next;
            curOdd.next = lastOdd.next;
            lastOdd.next = curOdd;
            lastOdd = curOdd;
            preEven = preEven.next;
            if (preEven != null)
                curOdd = preEven.next;
            else
                break;
        }
        return head;
    }
}
