package com.aaron.LeetCode;

import java.util.*;

//给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下： 
//
// 
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//} 
//
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。 
//
// 初始状态下，所有 next 指针都被设置为 NULL。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [1,2,3,4,5,6,7]
//输出：[1,#,2,3,#,4,5,6,7,#]
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 
//next 指针连接，'#' 标志着每一层的结束。
// 
//
// 
// 
//
// 示例 2: 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数量在
// [0, 2¹² - 1] 范围内 
// -1000 <= node.val <= 1000 
// 
//
// 
//
// 进阶： 
//
// 
// 你只能使用常量级额外空间。 
// 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 链表 二叉树 👍 953 👎 0


/**
 * 116, 填充每个节点的下一个右侧节点指针
 * @author Aaron Zhu
 * @date 2023-2-27
 */
public class PopulatingNextRightPointersInEachNode_116{
    
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
    }

    public static class Solution2 {
        public Node connect(Node root) {
            if(root==null) {
                return root;
            }

            Node parent = root;
            while ( parent.left!=null ) {
                Node cur = parent;
                while ( cur!=null ) {
                    cur.left.next = cur.right;
                    if( cur.next!=null ) {
                        cur.right.next = cur.next.left;
                    }
                    cur = cur.next;
                }
                parent = parent.left;
            }

            return root;
        }
    }

    public static class Solution1 {
        public Node connect(Node root) {
            if(root==null) {
                return root;
            }

            Deque<Node> queue = new LinkedList<>();
            queue.add( root );
            while ( !queue.isEmpty() ) {
                int size = queue.size();
                for (int i=0; i<size; i++) {
                    Node cur = queue.pollFirst();
                    if( i!=size-1 ) {
                        cur.next = queue.peekFirst();
                    }
                    if(cur.left!=null) {
                        queue.addLast( cur.left );
                    }
                    if(cur.right!=null) {
                        queue.addLast( cur.right );
                    }
                }
            }

            return root;
        }
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}