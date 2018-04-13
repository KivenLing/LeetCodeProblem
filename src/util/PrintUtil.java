package util;

import java.util.List;

/**
 * @author Kiven Ling
 * 2018/4/13 16:56
 * 打印list
 */
public class PrintUtil<T> {
    public static <T> void printList(List<T> lists){
        for (T t : lists) {
            System.out.print(t + " ");
        }
    }

    public static <T> void printListInList(List<List<T>> lists){
        for (List<T> list : lists) {
            printList(list);
            System.out.println();
        }
    }
}
