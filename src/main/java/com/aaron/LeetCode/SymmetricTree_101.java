package com.aaron.LeetCode;

import java.util.*;

//给你一个二叉树的根节点 root ， 检查它是否轴对称。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,2,3,4,4,3]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,2,null,3,null,3]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [1, 1000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1942 👎 0


/**
 * 101, 对称二叉树
 * @author Aaron Zhu
 * @date 2022-5-31
 */
public class SymmetricTree_101{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public boolean isSymmetric(TreeNode root) {
            TreeNode node1 = null;
            TreeNode node2 = null;
            Deque<TreeNode> queue = new LinkedList<>();
            queue.addLast(root);
            queue.addLast(root);

            while ( !queue.isEmpty() ) {
                node1 = queue.removeFirst();
                node2 = queue.removeFirst();

                if ( node1==null && node2==null ) {
                    continue;
                } else if ( node1==null || node2==null ) {
                    return false;
                } else if ( node1.val != node2.val ) {
                    return false;
                }

                queue.addLast( node1.left );
                queue.addLast( node2.right );

                queue.addLast( node1.right );
                queue.addLast( node2.left );
            }

            return true;
        }
    }

    public static class Solution1 {
        public boolean isSymmetric(TreeNode root) {
            return diff(root.left, root.right);
        }

        private boolean diff(TreeNode left, TreeNode right) {
            if( left==null && right==null ) {
                return true;
            }

            if( left==null || right==null ) {
                return false;
            }

            if( left.val != right.val ) {
                return false;
            }

            return diff(left.left, right.right) && diff(left.right, right.left);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
