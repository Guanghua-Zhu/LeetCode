package com.aaron.LeetCode;

//给你一棵二叉搜索树的
// root ，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
//输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
// 
//
// 示例 2： 
// 
// 
//输入：root = [5,1,7]
//输出：[1,null,5,null,7]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的取值范围是 [1, 100] 
// 0 <= Node.val <= 1000 
// 
//
// Related Topics 栈 树 深度优先搜索 二叉搜索树 二叉树 👍 327 👎 0


/**
 * 897, 递增顺序搜索树
 * @author Aaron Zhu
 * @date 2023-8-4
 */
public class IncreasingOrderSearchTree_897{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
    
    public static class Solution {
        public static TreeNode dummy = new TreeNode();

        public static TreeNode cur;

        public TreeNode increasingBST(TreeNode root) {
            cur = dummy;
            dfs(root);
            return dummy.right;
        }

        private void dfs(TreeNode node) {
            if( node==null ) {
                return;
            }

            dfs(node.left);

            TreeNode temp = new TreeNode(node.val);
            cur.right = temp;
            cur = temp;

            dfs(node.right);
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