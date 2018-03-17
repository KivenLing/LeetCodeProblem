package linkedlist;

import util.ListNode;
import util.MinHeap;

/*
ID:21 23
about Merge List
 */
public class MergeList {
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
    //这种方法会导致超时,原因没有采用归并排序的方法
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];
        ListNode head = lists[0];
        for (int i = 1; i < lists.length; i++){
            head = mergeTwoLists(head, lists[i]);
        }
        return head;
    }

    public static ListNode mergeKListsByHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];
        ListNode dummyHead = new ListNode();
        ListNode head = dummyHead;
        MinHeap nodeQueue = new MinHeap(2 * lists.length);
        for (ListNode viceHead : lists) {
            if (viceHead != null)
                nodeQueue.insert(viceHead);
        }
        while (!nodeQueue.isEmpty()){
            ListNode minNode = nodeQueue.extractMin();
            head.next = minNode;
            if (minNode.next != null)
                nodeQueue.insert(minNode.next);
        }
        return dummyHead.next;
    }

    //通过归并排序的思路，减少归并次数
    public static ListNode mergeKListsByMerge(ListNode[] lists) {
        if (lists.length == 0) return null;
        return helper(lists, 0, lists.length - 1);
    }
    private static ListNode helper(ListNode[] lists, int s, int e) {
        if (s == e) return lists[s];
        int m = s + (e - s) / 2;
        ListNode l = helper(lists, s, m);
        ListNode r = helper(lists, m + 1, e);
        return mergeTwoLists(l, r);
    }
}
