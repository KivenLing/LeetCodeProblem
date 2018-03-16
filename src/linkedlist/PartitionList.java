package linkedlist;

import util.ListNode;

/*
ID : 86
Given a linked list and a value x, partition it such
that all nodes less than x come before nodes greater
than or equal to x.

You should preserve the original relative order of
the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
 */
public class PartitionList {
    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;
        ListNode cur = head;
        ListNode less = null;
        ListNode preNode = null;
        //复杂度过高，易读性差
        while (cur != null){
            if (cur.val >= x || (preNode != null && preNode.val < x)){
                preNode = cur;
                if (cur.val < x)
                    less = cur;
                cur = cur.next;
                continue;
            }
            //cur.val < x || (preNode != null && preNode.val >= x) || preNode == null
            if (less == null){
                less = cur;
                cur = cur.next;
                if (preNode == null){
                    preNode = cur;
                }else {
                    less.next = head;
                    head = less;
                    preNode.next = cur;
                }
            }else {//less != null
                preNode.next = cur.next;
                cur.next = less.next;
                less.next = cur;
                less = cur;
                cur = preNode.next;
            }
        }
        return head;
    }
    public static void main(String[] args){
        int[] arr = {2,0,4,1,3,1,4,0,3};
        ListNode head = new ListNode(arr);
        head = partition(head, 4);
        System.out.println(head);
    }
}
