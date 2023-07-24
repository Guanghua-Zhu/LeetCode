package com.aaron.LeetCode;

import java.util.*;

//给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,1,4,null,2], k = 1
//输出：1
// 
//
// 示例 2： 
// 
// 
//输入：root = [5,3,6,2,4,null,null,1], k = 3
//输出：3
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数为 n 。 
// 1 <= k <= n <= 10⁴ 
// 0 <= Node.val <= 10⁴ 
// 
//
// 
//
// 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？ 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 754 👎 0


/**
 * 230, 二叉搜索树中第K小的元素
 * @author Aaron Zhu
 * @date 2023-7-24
 */
public class KthSmallestElementInABst_230{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        private Integer res = null;

        public int kthSmallest(TreeNode root, int k) {
            dfs(root, k);
            return res;
        }

        private int dfs(TreeNode node, int index) {
            if(node==null || res!=null) {
                return index;
            }

            index = dfs(node.left, index);

            index--;
            if( index==0 ) {
                res = node.val;
            }

            index = dfs(node.right, index);
            return index;
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