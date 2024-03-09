package com.wky.demo.interview.leetcode;

/**
 * @author wuming
 * @date 2024/3/9/03/09 08:45
 */
public class Demo {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);

        ListNode listNode = mergeTwoLists(l1, l2);


    }


    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode head = new ListNode(-1);
        ListNode curr = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        // 合并完l1或l2，最多只有一个未被合并完，我们直接将链表的末尾指向未合并完的链表即可
        curr.next = list1 == null ? list2 : list1;
        return head.next;
    }
}
