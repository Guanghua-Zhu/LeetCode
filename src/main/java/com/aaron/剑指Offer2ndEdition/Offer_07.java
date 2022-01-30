package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点
// 假设输入的前序遍历和中序遍历的结果中都不含重复的数字
//
// 示例 1:
// Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
// Output: [3,9,20,null,null,15,7]
// 
// 示例 2:
// Input: preorder = [-1], inorder = [-1]
// Output: [-1]
// 
// 限制：
// 0 <= 节点个数 <= 5000
// 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-
//preorder-and-inorder-traversal/ 
// Related Topics 树 数组 哈希表 分治 二叉树 
// 👍 656 👎 0

/**
 * 剑指 Offer 07, 重建二叉树
 * @author Aaron Zhu
 * @date 2022-1-30
 */
public class Offer_07 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if( preorder==null || preorder.length==0 ) {
                return null;
            }

            TreeNode root = build(preorder, inorder);
            return root;
        }

        private TreeNode build(int[] preorder, int[] inorder) {
            // 前序遍历结果的第一个值是根节点
            int rootVal = preorder[0];
            TreeNode root = new TreeNode( rootVal );
            int rootIndex = calcIndex(rootVal, inorder);

            // 左子树的节点数
            int leftSubTreeSize = rootIndex;
            // 右子树的节点数
            int rightSubTreeSize = inorder.length - rootIndex - 1;

            if( leftSubTreeSize>0 ) {
                // 左子树的前序数组
                int[] leftSubTreePreorder = Arrays.copyOfRange(preorder, 1, leftSubTreeSize+1);
                // 左子树的中序数组
                int[] leftSubTreeInorder = Arrays.copyOfRange(inorder, 0, rootIndex);
                TreeNode leftNode = build(leftSubTreePreorder, leftSubTreeInorder);
                root.left = leftNode;
            }

            if( rightSubTreeSize>0 ) {
                // 右子树的前序数组
                int[] rightSubTreePreorder = Arrays.copyOfRange(preorder, leftSubTreeSize+1, preorder.length);
                // 右子树的中序数组
                int[] rightSubTreeInorder = Arrays.copyOfRange(inorder, rootIndex+1, inorder.length);
                TreeNode rightNode = buildTree(rightSubTreePreorder, rightSubTreeInorder);
                root.right = rightNode;
            }

            return root;
        }

        /**
         * 计算元素在数组中的索引位置
         * @param num
         * @param inorder
         * @return
         */
        private int calcIndex(int num, int[] inorder) {
            int index = -1;
            for(int i=0; i<inorder.length; i++) {
                if( num == inorder[i] ) {
                    index = i;
                    break;
                }
            }
            return index;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

