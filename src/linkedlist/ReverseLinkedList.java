package linkedlist;

import util.ListNode;
// reverse List
// ID : 206 92 25
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
    Reverse a linked list from position m to n. Do it in-place and in
    one-pass.

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
        while (pos < m && curNode != null){
            ListNode tempNode = curNode;
            preNode = curNode;
            curNode = curNode.next;
            pos++;
        }
        //pos == m
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

    /*
    ID:25
    Given a linked list, reverse the nodes of a linked list k at a time
    and return its modified list.

    k is a positive integer and is less than or equal to the length of
    the linked list. If the number of nodes is not a multiple of k then
    left-out nodes in the end should remain as it is.

    You may not alter the values in the nodes, only nodes itself may be
    changed.

    Only constant memory is allowed.

    For example,
    Given this linked list: 1->2->3->4->5

    For k = 2, you should return: 2->1->4->3->5

    For k = 3, you should return: 3->2->1->4->5
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode cur = head;
        ListNode pre = dummyHead;
        while(canContinue(cur, k)){
            ListNode nextNode = getNext(cur, k);
            pre.next = reverseNode(cur, k);
            pre = cur;
            cur = nextNode;
        }
        return dummyHead.next;
    }

    private static ListNode reverseNode(ListNode head, int m){
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode pre = null;
        ListNode cur = dummyHead.next;
        int count = 0;
        while (count < m && cur != null){
            ListNode nextNode = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextNode;
            count++;
        }
        dummyHead.next = pre;
        head.next = cur;
        return dummyHead.next;
    }

    private static boolean canContinue(ListNode head, int k){
        if (head == null)
            return false;
        ListNode cur = head;
        int size = 0;
        while (cur != null){
            size++;
            if (size >= k)
                return true;
            cur = cur.next;
        }
        return false;
    }

    private static ListNode getNext(ListNode head, int k){
        ListNode cur = head;
        int size = 1;
        while (size <= k){
            size++;
            cur = cur.next;
        }
        return cur;
    }

    public static void main(String[] args){
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(arr);
        System.out.println(head);
        ListNode head2 = reverseBetween(head, 2, 4);
        System.out.println(head2);
        ListNode head3 = new ListNode(arr);
        System.out.println(reverseKGroup(head3, 2));
    }
}
