package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 输入一棵二叉树的根节点，求该树的深度
// 从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径
// 最长路径的长度为树的深度
//
// 例如：
// 给定二叉树 [3,9,20,null,null,15,7]
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 返回它的最大深度 3
//
// 提示：
// 节点总数 <= 10000
// 注意：本题与主站 104 题相同：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 162 👎 0

/**
 * 剑指 Offer 55 - I, 二叉树的深度
 * @author Aaron Zhu
 * @date 2022-1-30
 */
public class Offer_55_1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }


    /**
     * DFS: 后序遍历
     */
    public static class Solution {

        private int maxDepth;

        public int maxDepth(TreeNode root) {
            if( root==null ) {
                return 0;
            }

            int leftMaxDepth = maxDepth(root.left);
            int rightMaxDepth = maxDepth(root.right);
            int treeMaxDepth = Math.max(leftMaxDepth, rightMaxDepth) + 1;
            return treeMaxDepth;
        }
    }

    /**
     * DFS
     */
    public static class Solution1 {

        private int maxDepth;

        public int maxDepth(TreeNode root) {
            if( root==null ) {
                return 0;
            }
            maxDepth = 0;
            dfs(root, 0);
            return maxDepth;
        }

        private void dfs(TreeNode node, int currentDepth) {
            if( node==null ) {
                return;
            }
            // 处理当前节点
            currentDepth++;
            maxDepth = Math.max(currentDepth, maxDepth);
            // 处理左子树
            dfs(node.left, currentDepth );
            // 处理右子树
            dfs(node.right, currentDepth );
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
