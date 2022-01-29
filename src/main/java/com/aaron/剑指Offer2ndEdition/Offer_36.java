package com.aaron.剑指Offer2ndEdition;

//输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。 
//
// 
//
// 为了让您更好地理解问题，以下面的二叉搜索树为例： 
//
// 
//
// 
//
// 
//
// 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是
//第一个节点。 
//
// 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。 
//
// 
//
// 
//
// 
//
// 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。 
//
// 
//
// 注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-
//to-sorted-doubly-linked-list/ 
//
// 注意：此题对比原题有改动。 
// Related Topics 栈 树 深度优先搜索 二叉搜索树 链表 二叉树 双向链表 
// 👍 390 👎 0


/**
 * 剑指 Offer 36, 二叉搜索树与双向链表
 * @author Aaron Zhu
 * @date 2022-1-29
 */
public class Offer_36 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        private Node head;

        private Node pre;

        public Node treeToDoublyList(Node root) {
            if( root==null ) {
                return null;
            }

            dfs(root);
            // 构建循环列表
            head.left = pre;
            pre.right = head;
            return head;
        }

        private void dfs(Node node) {
            if( node==null ) {
                return;
            }

            dfs(node.left);

            // 前驱为空，表示访问的是搜索二叉树中值最小的一个节点
            if( pre==null ) {
                head = node;
            } else {    // 前驱不为空建立双向链表
                node.left = pre;
                pre.right =node;
            }

            // 更新前驱节点
            pre = node;

            dfs(node.right);
        }
    }

    public static class Solution2 {
        private Node head;

        private Node tail;

        public Node treeToDoublyList(Node root) {
            if( root==null ) {
                return null;
            }

            tail = head = root;
            sort(root);
            // 构建循环列表
            head.left = tail;
            tail.right = head;
            return head;
        }

        private Node[] sort(Node node) {
            // 对左子树进行排序
            Node[] leftSubTreeRange = new Node[]{node, node};
            if( node.left!=null ) {
                leftSubTreeRange = sort(node.left);
                node.left = leftSubTreeRange[1];
                leftSubTreeRange[1].right = node;
            }

            // 当前节点
            if( node.val < head.val) {
                head = node;
            }else if( node.val > tail.val ) {
                tail = node;
            }

            // 对右子树进行排序
            Node[] rightSubTreeRange = new Node[]{node, node};
            if( node.right!=null ) {
                rightSubTreeRange = sort(node.right);
                node.right = rightSubTreeRange[0];
                rightSubTreeRange[0].left = node;
            }

            return new Node[]{leftSubTreeRange[0], rightSubTreeRange[1]};
        }
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}

