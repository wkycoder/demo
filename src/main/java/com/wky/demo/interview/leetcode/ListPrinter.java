package com.wky.demo.interview.leetcode;

/**
 * @author wuming
 * @date 2024/4/2/04/02 09:57
 */
public class ListPrinter {


    public static void print(ListNode head) {
        while (head != null) {
            System.out.println(head.val + " ");
            head = head.next;
        }
    }


}
