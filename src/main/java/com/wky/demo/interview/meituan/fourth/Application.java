package com.wky.demo.interview.meituan.fourth;

/**
 * 第四题
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照顺序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相减，并以相同形式返回一个表示相减结果的链表。
 * 你可以假设
 * 1）除了数字 0 之外，这两个数都不会以 0 开头。
 * 2）给定的第一数字一定比第二个数字大。
 * 举例：
 * 输入：l1 = [9,8,7], l2 = [5,1,2]
 * 输出：[4,7,5]
 * 解释：987-512 = 475.
 * @author wuming
 * @date 2023/8/3/08/03 16:56
 */
public class Application {


    public static void main(String[] args) {
        Node l1 = new Node(9);
        l1.next = new Node(8);
        l1.next.next = new Node(7);
        l1.next.next.next = new Node(6);

        Node l2 = new Node(5);
        l2.next = new Node(1);
        l2.next.next = new Node(7);

        Node l3 = sub(l1, l2);
        printList(l3);
    }

    private static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data);
            head = head.next;
        }
    }

    private static Node sub(Node l1, Node l2) {
        // 首先需要将两个链表进行翻转
        l1 = reverseList(l1);
        l2 = reverseList(l2);

        // 定义一个新的链表，用于存储结果
        Node head = null; Node tail = null;
        // 记录借位
        int temp = 0;

        while (l1 != null || l2 != null) {
            int a = 0;
            int b = 0;
            if (l1 != null) {
                a = l1.data;
            }
            if (l2 != null) {
                b = l2.data;
            }
            // 相减
            int result = a - temp - b;
            if (result < 0) {
                // 不够减，需要向前面借1
                temp = 1;
                result = 10 + result;
            } else {
                temp = 0;
            }
            Node node = new Node(result);
            if (head == null) {
                head = tail  = node;
            } else {
                tail.next = node;
                tail = node;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return reverseList(head);
    }

    private static Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 双指针
        Node current = head;
        Node prev = null;
        Node temp;

        while (current != null) {
            temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }


}
