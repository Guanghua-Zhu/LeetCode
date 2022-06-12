package com.aaron.LeetCode;

import java.util.*;

//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：[2,1]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
// 
// 
// Related Topics 递归 链表 👍 2566 👎 0


/**
 * 206, 反转链表
 * @author Aaron Zhu
 * @date 2022-6-12
 */
public class ReverseLinkedList_206 {

    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode curNode = null;
            ListNode nextNode = head;
            while ( nextNode!=null ) {
                ListNode tempNode = nextNode.next;
                nextNode.next = curNode;

                curNode = nextNode;
                nextNode = tempNode;
            }
            return curNode;
        }

        private ListNode reverse(ListNode cur) {
            if(cur==null || cur.next==null) {
                return cur;
            }

            ListNode root = reverse(cur.next);
            ListNode nextNode = cur.next;
            nextNode.next = cur;
            cur.next = null;
            return root;
        }
    }

    public static class Solution1 {
        public ListNode reverseList(ListNode head) {
            ListNode root = reverse(head);
            return root;
        }

        private ListNode reverse(ListNode cur) {
            if(cur==null || cur.next==null) {
                return cur;
            }

            ListNode root = reverse(cur.next);
            ListNode nextNode = cur.next;
            nextNode.next = cur;
            cur.next = null;
            return root;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
