package com.aaron.LeetCode;

import java.util.*;

//给你一个链表的头节点 head ，判断链表中是否有环。 
//
// 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到
//链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。 
//
// 如果链表中存在环 ，则返回 true 。 否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 示例 3： 
//
// 
//
// 
//输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 10⁴] 
// -10⁵ <= Node.val <= 10⁵ 
// pos 为 -1 或者链表中的一个 有效索引 。 
// 
//
// 
//
// 进阶：你能用 O(1)（即，常量）内存解决此问题吗？ 
// Related Topics 哈希表 链表 双指针 👍 1510 👎 0


/**
 * 141, 环形链表
 * @author Aaron Zhu
 * @date 2022-6-4
 */
public class LinkedListCycle_141{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * Brent Algo
     */
    public static class Solution {
        public boolean hasCycle(ListNode head) {
            ListNode slow = head;
            ListNode fast = slow;
            // 快指针每轮过程中最多走几步
            int limit = 2;
            // 记录快指针在每轮过程中走的步数
            int step = 0;

            while ( fast!=null && fast.next!=null ) {
                // 快指针每次走1步
                fast = fast.next;
                step++;

                // 判断快、慢指针是否相遇, 即是否存在环
                if( fast == slow ) {
                    return true;
                }

                // 开启新一轮的同时, limit变为原来的2倍, 同时将慢指针移动到快指针的位置上
                if( step==limit ) {
                    step = 0;
                    limit = limit * 2;
                    slow = fast;
                }
            }

            return false;
        }
    }

    /**
     * Floyd Algo
     */
    public static class Solution1 {
        public boolean hasCycle(ListNode head) {
            ListNode slow = head;
            ListNode fast = slow;
            while ( fast!=null && fast.next!=null ) {
                slow = slow.next;
                fast = fast.next.next;
                if( slow==fast ) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}