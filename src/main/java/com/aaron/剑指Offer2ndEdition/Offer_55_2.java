package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 输入一棵二叉树的根节点，判断该树是不是平衡二叉树
// 如果某二叉树中任意节点的左右子树的深度相差不超过1
// 那么它就是一棵平衡二叉树
//
// 示例 1:
// 给定二叉树 [3,9,20,null,null,15,7]
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 返回 true
// 
// 示例 2:
// 给定二叉树 [1,2,2,3,3,null,null,4,4] 
//       1
//      / \
//     2   2
//    / \
//   3   3
//  / \
// 4   4
// 返回 false
//
// 限制： 
// 0 <= 树的结点个数 <= 10000
// 注意：本题与主站 110 题相同：https://leetcode-cn.com/problems/balanced-binary-tree/
// Related Topics 树 深度优先搜索 二叉树
// 👍 225 👎 0


/**
 * 剑指 Offer 55 - II, 平衡二叉树
 * @author Aaron Zhu
 * @date 2022-1-30
 */
public class Offer_55_2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        private static final int NOT_BALANCE = -1;

        public boolean isBalanced(TreeNode root) {
            if( root==null ) {
                return true;
            }

            int depth = calcDepth(root);
            if( depth>0 ) {
                return true;
            }
            return false;
        }

        private int calcDepth(TreeNode node) {
            if( node==null ) {
                return 0;
            }

            int leftSubTreeDepth = calcDepth(node.left);
            if( leftSubTreeDepth == NOT_BALANCE ) {
                return NOT_BALANCE;
            }

            int rightSubTreeDepth = calcDepth(node.right);
            if( rightSubTreeDepth == NOT_BALANCE ) {
                return NOT_BALANCE;
            }

            // 左、右子树各自均平衡, 则判断当前节点所在的树是否平衡
            if( Math.abs(leftSubTreeDepth-rightSubTreeDepth) <= 1 ) {
                //  当前节点所在的树平衡: 返回深度, 避免重复计算
                return Math.max(leftSubTreeDepth, rightSubTreeDepth) + 1;
            } else {    //  当前节点所在的树不平衡: 返回非平衡标记 -1
                return NOT_BALANCE;
            }
        }
    }

    public static class Solution1 {
        public boolean isBalanced(TreeNode root) {
            if( root==null ) {
                return true;
            }

            if (!isBalanced(root.left) || !isBalanced(root.right)) {
                return false;
            }

            int leftSubTreeDepth = calcDepth(root.left);
            int rightSubTreeDepth = calcDepth(root.right);
            int delta = Math.abs( leftSubTreeDepth - rightSubTreeDepth );
            if( delta<=1 ) {
                return true;
            }
            return false;
        }

        private int calcDepth(TreeNode node) {
            if( node==null ) {
                return 0;
            }

            int leftSubTreeDepth = calcDepth(node.left);
            int rightSubTreeDepth = calcDepth(node.right);
            int treeDepth = Math.max(leftSubTreeDepth, rightSubTreeDepth) + 1;
            return treeDepth;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
