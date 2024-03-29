package com.aaron.LeetCode;

import java.util.*;

//路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
//一定经过根节点。 
//
// 路径和 是路径中各节点值的总和。 
//
// 给你一个二叉树的根节点 root ，返回其 最大路径和 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6 
//
// 示例 2： 
//
// 
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围是 [1, 3 * 10⁴] 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1636 👎 0


/**
 * 124, 二叉树中的最大路径和
 * @author Aaron Zhu
 * @date 2022-6-27
 */
public class BinaryTreeMaximumPathSum_124{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        private int maxSum;

        public int maxPathSum(TreeNode root) {
            maxSum = Integer.MIN_VALUE;
            calc(root);
            return maxSum;
        }

        private int calc (TreeNode node) {
            if( node==null ) {
                return 0;
            }

            int leftSum =  calc(node.left);
            int rightSum = calc(node.right);

            maxSum = Math.max(maxSum, node.val+leftSum+rightSum );

            int curSum = node.val + Math.max(leftSum, rightSum);
            curSum = Math.max(curSum, 0);
            return curSum;
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

