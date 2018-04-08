package linkedlist;

import util.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Kiven Ling
 * 2018/4/8 23:13
 * 链表练习
 */
public class LinkedListWithRecursion {
    /*
    递归：
    数学归纳法正确性
    先一般，后特殊
    数据规模每次少1
     */
    public static ListNode createListNodes(List<Integer> data){
        if (data.isEmpty())
            return null;
        ListNode head = new ListNode(data.get(0));
        head.next = createListNodes(data.subList(1, data.size()));
        return head;
    }

    /**
     * reverse a linked list
     * @param head the head of nodes
     * @return head of reversed nodes
     */
    public static ListNode reverseNodes(ListNode head){
        if (head == null || head.next == null)
            return head;
        ListNode reverseHead = reverseNodes(head.next);
        head.next.next = head;
        head.next = null;
        return reverseHead;
    }
    public static void main(String[] args) {
        System.out.println(createListNodes(new ArrayList<>()));
        System.out.println(createListNodes(Collections.singletonList(1)));
        System.out.println(createListNodes(Arrays.asList(1, 2, 3, 4)));
        System.out.println(reverseNodes(createListNodes(Arrays.asList(1, 2, 3, 4))));
    }
}
