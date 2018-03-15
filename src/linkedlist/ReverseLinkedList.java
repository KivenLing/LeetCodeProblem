package linkedlist;

import util.ListNode;

public class ReverseLinkedList {
    // ID： 206
    // reverse List
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode tempNode = head;
        ListNode preNode = null;
        while(tempNode != null){
            ListNode nextNode = tempNode.next;
            tempNode.next = preNode;
            preNode = tempNode;
            tempNode = nextNode;
        }
        return preNode;
    }

    /*
    ID ： 92
    Reverse a linked list from position m to n. Do it in-place and in one-pass.

    For example:
    Given 1->2->3->4->5->NULL, m = 2 and n = 4,

    return 1->4->3->2->5->NULL.

    Note:
    Given m, n satisfy the following condition:
    1 ≤ m ≤ n ≤ length of list.
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m == n)
            return head;
        ListNode preNode = null;
        ListNode curNode = head;
        int pos = 1;
        //将curNode指向开始反转的node
        while (pos < m && curNode != null){
            ListNode tempNode = curNode;
            preNode = curNode;
            curNode = curNode.next;
            pos++;
        }
        //pos == m
        //记录开始revers的节点及它之前的节点
        ListNode rePre = preNode;
        ListNode reCur = curNode;
        while (pos <= n && curNode != null){
            ListNode nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
            pos++;
        }
        reCur.next = curNode;
        if (rePre == null)
            return preNode;
        rePre.next = preNode;
        return head;
    }

    public static void main(String[] args){
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(arr);
        System.out.println(head);
        ListNode head2 = reverseBetween(head, 2, 4);
        System.out.println(head2);
    }
}
