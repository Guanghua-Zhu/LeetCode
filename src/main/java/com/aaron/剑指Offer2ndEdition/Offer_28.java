package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
// 1 
// / \ 
// 2 2 
// / \ / \ 
//3 4 4 3 
//但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
// 1 
// / \ 
// 2 2 
// \ \ 
// 3 3 
//
// 
//
// 示例 1： 
//
// 输入：root = [1,2,2,3,4,4,3]
//输出：true
// 
//
// 示例 2： 
//
// 输入：root = [1,2,2,null,3,null,3]
//输出：false 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 1000 
//
// 注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 280 👎 0


/**
 * 剑指 Offer 28, 对称的二叉树
 * @author Aaron Zhu
 * @date 2022-1-29
 */
public class Offer_28 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * BFS
     */
    public static class Solution {
        public boolean isSymmetric(TreeNode root) {
            if( root==null ) {
                return true;
            }

            boolean result = compare(root.left, root.right);
            return result;
        }

        public boolean compare(TreeNode aTree, TreeNode bTree) {
            if( aTree==null && bTree==null ) {
                // 两个树同时越过叶子节点
                return true;
            } else if( aTree==null || bTree==null ) {
                // 一棵树未越过叶子节点, 另一棵树越过叶子节点
                return false;
            } else if( aTree.val!=bTree.val ) {
                return false;
            }

            return compare(aTree.left, bTree.right)
                && compare(aTree.right, bTree.left);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
