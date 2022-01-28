package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。 
//
// 
//
// 示例 1： 
//
// 输入：head = [1,3,2]
//输出：[2,3,1] 
//
// 
//
// 限制： 
//
// 0 <= 链表长度 <= 10000 
// Related Topics 栈 递归 链表 双指针 
// 👍 231 👎 0

/**
 * 剑指 Offer 06, 从尾到头打印链表
 * @author Aaron Zhu
 * @date 2022-1-28
 */
public class Offer_06 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int[] reversePrint(ListNode head) {
            LinkedList<Integer> list = new LinkedList<>();
            ListNode node = head;
            while (node!=null) {
                list.addFirst( node.val );
                node = node.next;
            }

            int size = list.size();
            int[] result = new int[size];
            for (int i=0; i<size; i++) {
                result[i] = list.pollFirst();
            }

            return result;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

