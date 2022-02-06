package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 输入一个链表，输出该链表中倒数第k个节点
// 为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点
//
// 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6
// 这个链表的倒数第 3 个节点是值为 4 的节点
//
// 示例： 
//给定一个链表: 1->2->3->4->5, 和 k = 2
//返回链表 4->5.
// Related Topics 链表 双指针 
// 👍 323 👎 0


/**
 * 剑指 Offer 22, 链表中倒数第k个节点
 * @author Aaron Zhu
 * @date 2022-2-6
 */
public class Offer_22 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 双指针
     */
    public static class Solution {
        public ListNode getKthFromEnd(ListNode head, int k) {
            ListNode fast = head;
            ListNode slow = head;
            // 快指针先走k步
            while (k > 0) {
                fast = fast.next;
                k--;
            }
            // 快、慢指针开始同步遍历
            while ( fast!=null ) {
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

