package com.aaron.LeetCode;

//给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。 
//
// k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 
//提示：
//
// 
// 链表中的节点数目为 n 
// 1 <= k <= n <= 5000 
// 0 <= Node.val <= 1000 
// 
//
// 
//
// 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？ 
//
// 
// 
// Related Topics 递归 链表 👍 1842 👎 0


/**
 * 25, K 个一组翻转链表
 * @author Aaron Zhu
 * @date 2022-11-7
 */
public class ReverseNodesInKGroup_25{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int k = 2;

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;

        ListNode node = solution.reverseKGroup(node1, k);
        System.out.println("gg");
    }

    public static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode();
            dummy.next = head;
            ListNode end = dummy;
            ListNode start = dummy;
            ListNode lastEndNode = dummy;

            while (start.next!=null) {
                start = start.next;
                end = goSteps(end, k);
                if( end == null ) {
                    break;
                }

                ListNode curNextNode = end.next;
                reverse(start,end);
                start.next = curNextNode;

                lastEndNode.next = end;
                lastEndNode = start;
                end = start;
            }

            return dummy.next;
        }

        private ListNode goSteps(ListNode node, int steps) {
            ListNode res = null;
            for (int i=0; i<steps; i++) {
                if(node==null) {
                    break;
                }
                node = node.next;
                res = node;
            }
            return res;
        }


        /**
         * 对 start -> ... -> end 链表进行反转, 结果为 end -> ... -> start
         * @param start
         * @param end
         * @return end
         */
        private void reverse(ListNode start, ListNode end) {
            ListNode tempEnd = end.next;
            ListNode dummy = null;
            ListNode prev = dummy;
            ListNode cur = start;
            ListNode next = null;

            while ( cur!=null && cur!=tempEnd ) {
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
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