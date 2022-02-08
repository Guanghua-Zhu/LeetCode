package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//输入两个链表，找出它们的第一个公共节点。 
//
// 如下面的两个链表： 
//
// 
//
// 在节点 c1 开始相交。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, s
//kipB = 3
//输出：Reference of the node with value = 8
//输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1
//,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
// 
//
// 
//
// 示例 2： 
//
// 
//
// 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB =
// 1
//输出：Reference of the node with value = 2
//输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4
//]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
// 
//
// 
//
// 示例 3： 
//
// 
//
// 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
//输出：null
//输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而
// skipA 和 skipB 可以是任意值。
//解释：这两个链表不相交，因此返回 null。
// 
//
// 
//
// 注意： 
//
// 
// 如果两个链表没有交点，返回 null. 
// 在返回结果后，两个链表仍须保持原有的结构。 
// 可假定整个链表结构中没有循环。 
// 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。 
// 本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lis
//ts/ 
// 
// Related Topics 哈希表 链表 双指针 
// 👍 423 👎 0


/**
 * 剑指 Offer 52, 两个链表的第一个公共节点
 * @author Aaron Zhu
 * @date 2022-2-7
 */
public class Offer_52 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 交叉遍历
     */
    public static class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if( headA==null || headB==null ) {
                return null;
            }

            ListNode node1 = headA;
            ListNode node2 = headB;
            while ( node1 != node2 ) {
                if ( node1!=null ) {
                    node1 = node1.next;
                } else {
                    // 遍历完A链表再遍历B链表
                    node1 = headB;
                }

                if ( node2!=null ) {
                    node2 = node2.next;
                } else {
                    // 遍历完B链表再遍历A链表
                    node2 = headA;
                }
            }

            return node1;
        }
    }

    /**
     * 快慢指针
     */
    public static class Solution1 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if( headA==null || headB==null ) {
                return null;
            }

            ListNode nodeA = headA;
            ListNode nodeB = headB;
            int sizeA = 0;
            int sizeB = 0;

            while ( nodeA!=null || nodeB!=null ) {
                if( nodeA!=null ) {
                    nodeA = nodeA.next;
                    sizeA++;
                }
                if( nodeB!=null ) {
                    nodeB = nodeB.next;
                    sizeB++;
                }
            }

            ListNode fast = null;
            ListNode slow = null;
            if( (sizeA - sizeB)>0 ) {
                fast = headA;
                slow = headB;
            } else {
                fast = headB;
                slow = headA;
            }

            int diffSize = Math.abs(sizeA - sizeB);
            while (diffSize>0) {
                fast = fast.next;
                diffSize--;
            }

            ListNode result = null;
            while (fast!=null && slow!=null) {
                if( fast == slow ) {
                    result = fast;
                    break;
                }
                fast = fast.next;
                slow = slow.next;
            }

            return result;
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
