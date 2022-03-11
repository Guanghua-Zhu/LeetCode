package com.aaron.Leetcode;

import java.math.BigInteger;
import java.util.*;

//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 
// 👍 7667 👎 0

/**
 * 2, 两数相加
 * @author Aaron Zhu
 * @date 2022-3-11
 */
public class AddTwoNumbers_2{
    public static void main(String[] args) {
        System.out.println("gg");
    }

    /**
     * 递归
     */
    public static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = new ListNode(0);
            add(l1, l2, head);
            return head.next;
        }

        private void add(ListNode l1, ListNode l2, ListNode res) {
            if(l1==null && l2==null) {
                if( res.val>=10 ) {
                    res.next = new ListNode( res.val / 10 );
                    res.val = res.val % 10;
                }
                return;
            }

            if( l1==null ) {
                int tempNum = l2.val + res.val/10;
                res.next = new ListNode( tempNum );
                res.val = res.val % 10;
                add(null, l2.next, res.next);
            } else if( l2==null ) {
                int tempNum = l1.val + res.val/10;
                res.next = new ListNode( tempNum );
                res.val = res.val % 10;
                add(l1.next, null, res.next);
            } else {    // l1, l2 均不为 null
                int tempNum = l1.val + l2.val + res.val/10;
                res.next = new ListNode( tempNum );
                res.val = res.val % 10;
                add(l1.next, l2.next, res.next);
            }
        }
    }

    /**
     * 大数+迭代
     */
    public static class Solution1 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            BigInteger num1 = BigInteger.valueOf(0);
            BigInteger num2 = BigInteger.valueOf(0);
            BigInteger digit = BigInteger.valueOf(1);

            while (l1!=null || l2!=null) {
                if( l1!=null ) {
                    BigInteger temp = digit.multiply( BigInteger.valueOf(l1.val) );
                    num1 = num1.add( temp );
                    l1 = l1.next;
                }
                if( l2!=null ) {
                    BigInteger temp = digit.multiply( BigInteger.valueOf(l2.val) );
                    num2 = num2.add( temp );
                    l2 = l2.next;
                }
                digit = digit.multiply( BigInteger.valueOf(10) );
            }

            BigInteger num3 = num1.add(num2);
            BigInteger ten = BigInteger.valueOf(10);
            BigInteger zero = BigInteger.valueOf(0);
            ListNode current = new ListNode();
            ListNode head = current;

            do {
                int tempNum = num3.mod( ten ).intValue();
                current.next = new ListNode( tempNum );
                num3 = num3.divide( ten );
                current = current.next;
            } while ( !num3.equals(zero) );

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
