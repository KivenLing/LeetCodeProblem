package linkedlist;

import util.LinkedListHelper;
import util.ListNode;

import java.util.ArrayList;
import java.util.List;

/*
ID : 143
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */
public class ReorderList {
    //将nodes存入list中，相当于对数组操作，这样就方便了
    public static void reorderList(ListNode head) {
        List<ListNode> nodes = new ArrayList<>();
        ListNode cur = head;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = null;
            nodes.add(cur);
            cur = next;
        }
        int size = nodes.size();
        int mid = size / 2;
        int sign = size % 2;
        for (int i = 0; i < mid; i++) {
            nodes.get(i).next = nodes.get(size - i - 1);
            if (sign == 0 && i == mid - 1)
                continue;
            nodes.get(size - i - 1).next = nodes.get(i + 1);
        }
    }

    //找到链表中部节点，reverse，这样添加顺寻就ok了
    public static void reorderListByReverse(ListNode head){
        if (head == null || head.next == null)
            return;
        ListNode slow = head;
        ListNode quick = head;
        while (quick.next != null && quick.next.next != null){
            slow = slow.next;
            quick =quick.next.next;
        }
        //index(slow) * 2 - 1 = index(quick)
        slow.next = ReverseLinkedList.reverseList(slow.next);
        quick = slow.next;
        slow.next = null;
        ListNode cur = head;
        while (quick != null){
            ListNode curNext = cur.next;
            ListNode midNext = quick.next;
            cur.next = quick;
            quick.next = curNext;
            cur = curNext;
            quick = midNext;
        }
    }

    /*
    ID:234
    Given a singly linked list, determine if it is a palindrome.

    Follow up:
    Could you do it in O(n) time and O(1) space?
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode slow = head;
        ListNode quick = head;
        while (quick.next != null && quick.next.next != null){
            slow = slow.next;
            quick =quick.next.next;
        }
        slow.next = ReverseLinkedList.reverseList(slow.next);
        ListNode mid = slow.next;
        ListNode cur = head;
        boolean isPalindrome = true;
        while (mid != null){
            if (cur.val != mid.val){
                isPalindrome = false;
                break;
            }
            cur = cur.next;
            mid = mid.next;
        }
        slow.next = ReverseLinkedList.reverseList(slow.next);
        return isPalindrome;
    }
}


