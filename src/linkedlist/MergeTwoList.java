package linkedlist;

import util.ListNode;

public class MergeTwoList {
    /*
    ID: 21
    Merge two sorted linked lists and return it as a new list.
    The new list should be made by splicing together the nodes
    of the first two lists.
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null){
            if (l1.val > l2.val){
                cur.next = l2;
                l2 = l2.next;
            }else {
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        if (l1 != null)
            cur.next = l1;
        else
            cur.next = l2;
        return dummyHead.next;
    }

    //使用递归，代码简洁
    public static ListNode mergeTwoListsByDG(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /*
    ID:23
    Merge k sorted linked lists and return it as one sorted list.
    Analyze and describe its complexity.
     */
    public static ListNode mergeKLists(ListNode[] lists) {

        return null;
    }
}
