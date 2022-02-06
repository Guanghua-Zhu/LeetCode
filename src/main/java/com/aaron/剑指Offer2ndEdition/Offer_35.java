package com.aaron.剑指Offer2ndEdition;

import java.util.*;
import java.util.regex.Pattern;

// 请实现 copyRandomList 函数，复制一个复杂链表
// 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点
// 还有一个 random 指针指向链表中的任意节点或者 null
//
// 示例 1： 
// 输入：head = [ [7,null],[13,0],[11,4],[10,2],[1,0] ]
// 输出：[ [7,null],[13,0],[11,4],[10,2],[1,0] ]
//
// 示例 2： 
// 输入：head = [ [1,1],[2,1] ]
// 输出：[ [1,1],[2,1] ]
// 
// 示例 3：
// 输入：head = [ [3,null],[3,0],[3,null] ]
// 输出：[ [3,null],[3,0],[3,null] ]
//
// 示例 4： 
// 输入：head = []
// 输出：[]
// 解释：给定的链表为空（空指针），因此返回 null。
// 
// 提示：
// -10000 <= Node.val <= 10000
// Node.random 为空（null）或指向链表中的节点。 
// 节点数目不超过 1000 。 
// 
// 注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
// Related Topics 哈希表 链表
// 👍 408 👎 0

/**
 * 剑指 Offer 35, 复杂链表的复制
 * @author Aaron Zhu
 * @date 2022-2-6
 */
public class Offer_35 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 哈希表
     */
    public static class Solution {
        public Node copyRandomList(Node head) {
            if( head==null ) {
                return null;
            }

            Map<Node, Node> old2NewMap = new HashMap<>();
            Node currentNode = head;
            while (currentNode!=null) {
                Node newNode = new Node( currentNode.val );
                old2NewMap.put(currentNode, newNode);
                currentNode = currentNode.next;
            }

            currentNode = head;
            while (currentNode!=null) {
                Node newNode = old2NewMap.get(currentNode);
                newNode.next = old2NewMap.get( currentNode.next );
                newNode.random = old2NewMap.get( currentNode.random );
                currentNode = currentNode.next;
            }

            return old2NewMap.get(head);
        }
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}

