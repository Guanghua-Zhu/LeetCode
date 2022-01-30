package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//给定一棵二叉搜索树，请找出其中第 k 大的节点的值。 
//
// 
//
// 示例 1: 
//
// 
//输入: root = [3,1,4,null,2], k = 1
//   3
//  / \
// 1   4
//  \
//   2
//输出: 4 
//
// 示例 2: 
//
// 
//输入: root = [5,3,6,2,4,null,null,1], k = 3
//       5
//      / \
//     3   6
//    / \
//   2   4
//  /
// 1
//输出: 4 
//
// 
//
// 限制： 
//
// 
// 1 ≤ k ≤ 二叉搜索树元素个数 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 
// 👍 237 👎 0


/**
 * 剑指 Offer 54, 二叉搜索树的第k大节点
 * @author Aaron Zhu
 * @date 2022-1-30
 */
public class Offer_54 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        private Integer result;

        private Integer index;

        public int kthLargest(TreeNode root, int k) {
            index = 0;
            dfs(root, k);
            return result;
        }

        private void dfs(TreeNode node, int k) {
            if( node.right!=null ) {
                dfs(node.right, k);
            }

            index++;
            if( index == k ) {
                result = node.val;
                return;
            } else if( index>k ) {
                return;
            }

            if( node.left!=null ) {
                dfs(node.left, k);
            }
        }

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

