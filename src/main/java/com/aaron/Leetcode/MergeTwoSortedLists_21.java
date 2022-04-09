package com.aaron.Leetcode;

import java.util.*;

//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 2329 👎 0


/**
 * 21, 合并两个有序链表
 * @author Aaron Zhu
 * @date 2022-4-8
 */
public class MergeTwoSortedLists_21{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode head = new ListNode();
            ListNode cur = head;
            while ( list1!=null || list2!=null ) {
                if( list1==null || (list2!=null && list2.val<list1.val) ) {
                    cur.next = list2;
                    list2 = list2.next;
                } else if( list2==null || (list1!=null && list1.val<=list2.val) ) {
                    cur.next = list1;
                    list1 = list1.next;
                }
                cur = cur.next;
            }
            return head.next;
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

