package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。 
//
// 示例1： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4 
//
// 限制： 
//
// 0 <= 链表长度 <= 1000 
//
// 注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/ 
// Related Topics 递归 链表 
// 👍 204 👎 0


/**
 * 剑指 Offer 25, 合并两个排序的链表
 * @author Aaron Zhu
 * @date 2022-2-6
 */
public class  Offer_25 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode head = new ListNode(0);
            ListNode currentNode = head;
            while ( l1!=null || l2!=null ) {
                if( l1==null ) {
                    currentNode.next = new ListNode( l2.val );
                    l2 = l2.next;
                } else if( l2==null ) {
                    currentNode.next = new ListNode(l1.val);
                    l1 = l1.next;
                } else if( l1.val <= l2.val ) {
                    currentNode.next = new ListNode(l1.val);
                    l1 = l1.next;
                } else if( l1.val > l2.val ) {
                    currentNode.next = new ListNode( l2.val );
                    l2 = l2.next;
                }

                currentNode = currentNode.next;
            }
            return head.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

