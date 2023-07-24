package com.aaron.LeetCode;

import java.util.*;

//给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。 
//
// 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。 
//
// 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。 
//
// 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: head = [1,2,3,4,5]
//输出: [1,3,5,2,4] 
//
// 示例 2: 
//
// 
//
// 
//输入: head = [2,1,3,5,6,4,7]
//输出: [2,3,6,7,1,5,4] 
//
// 
//
// 提示: 
//
// 
// n == 链表中的节点数 
// 0 <= n <= 10⁴ 
// -10⁶ <= Node.val <= 10⁶ 
// 
//
// Related Topics 链表 👍 714 👎 0


/**
 * 328, 奇偶链表
 * @author Aaron Zhu
 * @date 2023-7-24
 */
public class OddEvenLinkedList_328{
    
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
    }

    public static class Solution3 {
        public ListNode oddEvenList(ListNode head) {
            if( head==null ) {
                return head;
            }

            ListNode oddHead = head;
            ListNode evenHead = head.next;
            ListNode odd = oddHead;
            ListNode even = evenHead;

            while ( even!=null && even.next!=null ) {
                // 处理奇节点
                odd.next = even.next;
                odd = odd.next;

                // 处理偶节点
                even.next = odd.next;
                even = even.next;
            }

            odd.next = evenHead;
            return oddHead;
        }
    }

    public static class Solution2 {
        public ListNode oddEvenList(ListNode head) {
            ListNode dummy = new ListNode();
            dummy.next = head;
            ListNode cur = dummy;

            int count = 0;
            ListNode oddList = new ListNode();
            ListNode evenList = new ListNode();
            ListNode oddCur = oddList;
            ListNode evenCur = evenList;

            while ( cur.next != null ) {
                cur = cur.next;
                count++;

                if( count%2==1 )  {   // 奇数
                    oddCur.next = cur;
                    oddCur = oddCur.next;
                } else {        // 偶数
                    evenCur.next = cur;
                    evenCur = evenCur.next;
                }
            }
            // 将最后一个偶数节点的后继指针 与 链表中最后一个节点断开，防止成环
            evenCur.next = null;

            oddCur.next = evenList.next;
            return dummy.next;
        }
    }

    /**
     * 暴力法
     */
    public static class Solution1 {
        public ListNode oddEvenList(ListNode head) {
            List<Integer> list = new ArrayList<>();
            ListNode cur = head;
            while (cur!=null) {
                list.add( cur.val );
                cur = cur.next;
            }

            ListNode dummy = new ListNode();
            cur = dummy;
            for (int i=0; i<2; i++) {
                for (int j=i; j<list.size(); j=j+2) {
                    ListNode temp = new ListNode(list.get(j));
                    cur.next = temp;
                    cur = cur.next;
                }
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