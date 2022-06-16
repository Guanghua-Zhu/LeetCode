package com.aaron.LeetCode;

//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
//
// 
//
// 示例 : 
//给定二叉树 
//
//           1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。 
//
// 
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。 
// Related Topics 树 深度优先搜索 二叉树 👍 1063 👎 0


/**
 * 543, 二叉树的直径
 * @author Aaron Zhu
 * @date 2022-6-15
 */
public class DiameterOfBinaryTree_543{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        private int res;
        public int diameterOfBinaryTree(TreeNode root) {
            res = 0;
            calcDeep(root);
            return res;
        }

        private int calcDeep(TreeNode cur) {
            if( cur==null ) {
                return 0;
            }

            int L = calcDeep(cur.left);
            int R = calcDeep(cur.right);
            res = Math.max(res, L+R);
            return Math.max(L,R) + 1;
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