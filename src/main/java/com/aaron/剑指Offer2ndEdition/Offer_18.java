package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点返回删除后的链表的头节点
// 注意：此题对比原题有改动
//
// 示例 1: 
// 输入: head = [4,5,1,9], val = 5
// 输出: [4,1,9]
// 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9
//
// 示例 2: 
// 输入: head = [4,5,1,9], val = 1
// 输出: [4,5,9]
// 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
//
// 说明： 
// 题目保证链表中节点的值互不相同
// 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点 
// Related Topics 链表
// 👍 190 👎 0


/**
 * 剑指 Offer 18, 删除链表的节点
 * @author Aaron Zhu
 * @date 2022-2-6
 */
public class Offer_18 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 双指针
     */
    public static class Solution {
        public ListNode deleteNode(ListNode head, int val) {
            // 增加一个表头, 便于处理链表第一个元素
            ListNode newHead = new ListNode(0);
            newHead.next = head;
            // 双指针
            ListNode pre = newHead;
            ListNode current = newHead.next;

            while ( current!=null) {
                if( current.val == val ) {
                    // 删除当前节点
                    pre.next = current.next;
                    current.next = null;
                    // 更新当前节点
                    current = pre.next;
                } else {
                    pre  = current;
                    current = current.next;
                }
            }

            return newHead.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
