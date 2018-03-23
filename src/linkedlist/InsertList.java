package linkedlist;

import util.ArrayHelper;
import util.ListNode;

import java.util.ArrayList;
import java.util.List;


public class InsertList {
    //ID:147
    //Sort a linked list using insertion sort.
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode cur = head.next;
        ListNode curPre = head;
        while (cur != null){
            ListNode pre = dummyHead;
            ListNode begin = dummyHead.next;
            ListNode next = cur.next;
            //找到比cur.val大的节点
            while (begin != cur && cur.val >= begin.val){
                pre = begin;
                begin = begin.next;
            }
            if (begin.val > cur.val){
                curPre.next = next;
                cur.next = begin;
                pre.next = cur;
                cur = next;
            }else {
                curPre = curPre.next;
                cur = next;
            }
        }
        return dummyHead.next;    }

    /*
    ID :148
    Sort a linked list in O(n log n) time using constant space complexity.
     */
    //Merge
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        int t = (int)Math.ceil(size(head)/ 8.0);
        ListNode cur = head;
        List<ListNode> nodes = new ArrayList<>();
        for (int i = 0; i < t; i++){
            ListNode sortNode = sort(cur, 8);
            ListNode tail = getTail(sortNode, 8);
            nodes.add(sortNode);
            if (tail == null)
                break;
            cur = tail.next;
            tail.next = null;
        }
        int length = nodes.size();

        return helper(nodes, 0, length - 1);
    }

    private static ListNode helper(List<ListNode> nodes, int l, int r){
        if (l == r)
            return nodes.get(l);
        int mid = (l + r) / 2;
        ListNode lNode = helper(nodes, l, mid);
        ListNode rNode = helper(nodes, mid + 1, r);
        return MergeList.mergeTwoLists(lNode, rNode);
    }
    private static ListNode sort(ListNode head, int n){
        if (head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode cur = head.next;
        ListNode curPre = head;
        int t = 1;
        while (cur != null && t < n){
            ListNode pre = dummyHead;
            ListNode begin = dummyHead.next;
            ListNode next = cur.next;
            while (begin != cur && cur.val >= begin.val){
                pre = begin;
                begin = begin.next;
            }
            if (begin.val > cur.val){
                curPre.next = next;
                cur.next = begin;
                pre.next = cur;
            }else {
                curPre = curPre.next;
            }
            cur = next;
            t++;
        }
        return dummyHead.next;
    }
    private static ListNode getTail(ListNode head, int n){
        int size = 1;
        while (size < n && head != null){
            size++;
            head = head.next;
        }
        return head;
    }

    private static int size(ListNode head){
        int size = 0;
        while (head != null){
            size++;
            head = head.next;
        }
        return size;
    }
    public static void main(String[] args){
        int[] arr = ArrayHelper.generateRandomArray(20);
        ListNode head = new ListNode(arr);
        //ListNode head2 = insertionSortList(head);
        ListNode head3 = sortList(head);
       // System.out.println(head2);
        System.out.println(head3);
    }
}
