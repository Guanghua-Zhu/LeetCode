package com.aaron.剑指Offer2ndEdition;

//定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
//
// 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 206 题相同：https://leetcode-cn.com/problems/reverse-linked-list/ 
// Related Topics 递归 链表 
// 👍 366 👎 0


/**
 * 剑指 Offer 24, 反转链表
 * @author Aaron Zhu
 * @date 2022-2-6
 */
public class Offer_24 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode newListNode = solution.reverseList(node1);
        System.out.println("gg");
    }

    /**
     * 快慢指针
     */
    public static class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode fast = head;
            ListNode slow = null;

            while ( fast!=null ) {
                ListNode temp = fast.next;
                fast.next = slow;
                slow = fast;
                fast = temp;
            }

            return slow;
        }
    }

    /**
     * 递归
     */
    public static class Solution2 {
        public ListNode reverseList(ListNode head) {
            ListNode listNode = reverse(head, null);
            return listNode;
        }

        private ListNode reverse(ListNode currentNode, ListNode preNode) {
            if( currentNode==null ) {
                // 返回头节点
                return preNode;
            }

            // 递归处理原链表的下一个节点
            ListNode head = reverse(currentNode.next, currentNode);
            // 修改节点间的指向
            currentNode.next = preNode;
            return head;
        }
    };

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

