package com.aaron.LeetCode;

//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针 
// 👍 1918 👎 0


/**
 * 19, 删除链表的倒数第 N 个结点
 * @author Aaron Zhu
 * @date 2022-3-29
 */
public class RemoveNthNodeFromEndOfList_19{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 快慢指针
     */
    public static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummyNode = new ListNode();
            dummyNode.next = head;

            ListNode fast = dummyNode;
            ListNode slow = dummyNode;

            // 快指针先走N+1步
            n++;
            while (n>0) {
                fast = fast.next;
                n--;
            }

            // 快慢指针同步遍历
            while (fast!=null) {
                fast = fast.next;
                slow = slow.next;
            }

            // 此时慢指针指向的是待删除节点的前驱
            slow.next = slow.next.next;
            return dummyNode.next;
        }

    }

    /**
     * 递归法
     */
    public static class Solution1 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode newHead = new ListNode();
            newHead.next = head;
            remove(newHead, newHead.next, n);
            return newHead.next;
        }

        private int remove(ListNode pre, ListNode cur, int n) {
            if( cur==null ) {
                return 0;
            }

            int num = remove(cur, cur.next, n);
            num++;
            if(num == n) {
                pre.next = cur.next;
            }
            return num;
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
