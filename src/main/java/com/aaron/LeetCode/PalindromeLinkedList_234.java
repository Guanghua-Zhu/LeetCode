package com.aaron.LeetCode;

import java.util.*;

//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 10⁵] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 栈 递归 链表 双指针 👍 1415 👎 0


/**
 * 234, 回文链表
 * @author Aaron Zhu
 * @date 2022-6-13
 */
public class PalindromeLinkedList_234{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public boolean isPalindrome(ListNode head) {
            if( head==null ) {
                return true;
            }

            ListNode list1EndNode = getHalf(head);
            ListNode list2 = reverseList( list1EndNode.next );

            ListNode p1 = head;
            ListNode p2 = list2;
            boolean res = true;
            while ( res && p2 !=null) {
                if( p1.val != p2.val ) {
                    res = false;
                }

                p1 = p1.next;
                p2 = p2.next;
            }

            ListNode node3 = reverseList( list2 );
            list1EndNode.next = node3;
            return res;
        }

        private ListNode getHalf(ListNode node) {
            ListNode slow = node;
            ListNode fast = node;

            while ( fast.next!=null && fast.next.next!=null ) {
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }

        private ListNode reverseList(ListNode node) {
            ListNode prev = null;
            ListNode cur = node;
            while ( cur!=null ) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            return prev;
        }

    }

    public static class Solution1 {
        public boolean isPalindrome(ListNode head) {
            boolean res = true;
            LinkedList<Integer> stack = new LinkedList<>();
            ListNode cur = head;
            while ( cur!=null ) {
                stack.addLast( cur.val );
                cur = cur.next;
            }

            cur = head;
            while ( !stack.isEmpty() && cur!=null ) {
                Integer num1 = stack.removeLast();
                Integer num2 = cur.val;
                if(num1 != num2) {
                    res = false;
                    break;
                }
                cur = cur.next;
            }

            return res;
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


