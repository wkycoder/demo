package com.wky.demo.interview.leetcode;

/**
 * @author wuming
 * @date 2024/4/2/04/02 09:55
 */
public class ReverseListTwo {


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode listNode = reverseBetween(head, 2, 4);
        ListPrinter.print(listNode);
    }


    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 记录i位置的前一个节点
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        // prev表示待反转区域的第一个节点的前一个节点
        ListNode curr = prev.next;
        // 指向curr的下一个节点
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = curr.next;
            curr.next = next.next;
            // 如果将 next.next 指向当前节点 curr，则会导致链表断裂，因为在进行反转操作时，
            // 当前节点 curr 的 next 已经被修改为了待反转区域的下一个节点。
            // 此时，next.next需要指向的prev.next节点，而不是curr，curr在经历一次反转后，已经不是prev.next节点了。
            next.next = prev.next;
            prev.next = next;
        }
        return dummy.next;
    }





}
