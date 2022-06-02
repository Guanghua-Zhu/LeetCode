package com.aaron.LeetCode;

import sun.reflect.generics.tree.Tree;

import java.util.*;

//给定一个二叉树，找出其最大深度。 
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 
//给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1262 👎 0


/**
 * 104, 二叉树的最大深度
 * @author Aaron Zhu
 * @date 2022-6-2
 */
public class MaximumDepthOfBinaryTree_104{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * DFS
     */
    public static class Solution {
        private int maxDepth;

        public int maxDepth(TreeNode root) {
            maxDepth = 0;
            dfs(root, 0);
            return maxDepth;
        }

        private void dfs(TreeNode node, int currentDepth) {
            if( node==null ) {
                maxDepth = Math.max(maxDepth, currentDepth);
                return;
            }

            currentDepth++;
            dfs(node.left, currentDepth);
            dfs(node.right, currentDepth);
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

