package util;

public class LinkedListHelper {
    private LinkedListHelper(){}

    //返回这个链表第i个节点
    public static ListNode getnthNodeOfList(ListNode head, int n){
        int size = 1;
        while (size < n && head != null){
            size++;
            head = head.next;
        }
        return head;
    }

    public static int sizeOfNode(ListNode head){
        int size = 0;
        while (head != null){
            size++;
            head = head.next;
        }
        return size;
    }

    public static ListNode getLastNode(ListNode head){
        if (head == null)
            return null;
        while (head.next != null){
            head = head.next;
        }
        return head;
    }
}
