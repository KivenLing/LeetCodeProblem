package linkedlist;

import util.ListNode;

/*
ID:203
Remove all elements from a linked list of integers
that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5

Credits:
Special thanks to @mithmatt for adding this problem and
creating all test cases.
 */
public class RemoveElements {
    //特殊处理头节点
    public static ListNode removeElements(ListNode head, int val){
        if(head == null)
            return head;
        ListNode cur = head.next;
        ListNode pre = head;
        while (cur != null){
            if(cur.val == val){
                pre.next = cur.next;
                cur = cur.next;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        if(head.val == val)
            return head.next;
        return head;
    }

    //设立虚拟头节点
    public static ListNode removeElementsII(ListNode head, int val){
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode cur = head;
        ListNode pre = dummyHead;
        while (cur != null){
            if(cur.val == val){
                pre.next = cur.next;
                cur = cur.next;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }
}
