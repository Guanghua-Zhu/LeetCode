package com.aaron.LeetCode;

import java.util.*;

//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
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
// 链表中节点的数目在范围 [0, 5 * 10⁴] 内 
// -10⁵ <= Node.val <= 10⁵ 
// 
//
// 
//
// 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// Related Topics 链表 双指针 分治 排序 归并排序 👍 1660 👎 0


/**
 * 148, 排序链表
 * @author Aaron Zhu
 * @date 2022-6-25
 */
public class SortList_148{
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode node1 = new ListNode(4);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(1);
        node1.next.next.next = new ListNode(3);
        solution.sortList(node1);
        System.out.println("gg");
    }

    /**
     * 归并排序
     */
    public static class Solution {
        public ListNode sortList(ListNode head) {
            return sort(head);
        }

        private ListNode sort(ListNode node) {
            if( node==null || node.next==null ) {
                return node;
            }

            ListNode fast = node;
            ListNode slow = node;
            while ( fast.next!=null && fast.next.next!=null ) {
                slow = slow.next;
                fast = fast.next.next;
            }

            ListNode list1 = node;
            ListNode list2 = slow.next;
            slow.next = null;

            list1 = sort( list1 );
            list2 = sort( list2 );
            ListNode res = merge(list1, list2);
            return res;
        }

        private ListNode merge(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode();
            ListNode cur = dummy;

            while ( list1!=null || list2!=null ) {
                if( list1==null ) {
                    cur.next = list2;
                    list2 = list2.next;
                } else if( list2 ==null ) {
                    cur.next = list1;
                    list1 = list1.next;
                } else if( list1.val< list2.val ){
                    cur.next = list1;
                    list1 = list1.next;
                } else if( list1.val >= list2.val ) {
                    cur.next = list2;
                    list2 = list2.next;
                }

                cur = cur.next;
            }

            return dummy.next;
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