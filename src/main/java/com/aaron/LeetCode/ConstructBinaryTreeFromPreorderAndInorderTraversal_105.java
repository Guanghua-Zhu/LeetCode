package com.aaron.LeetCode;

import sun.reflect.generics.tree.Tree;

import java.util.*;

//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。 
//
// 
//
// 示例 1: 
//
// 
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= preorder.length <= 3000 
// inorder.length == preorder.length 
// -3000 <= preorder[i], inorder[i] <= 3000 
// preorder 和 inorder 均 无重复 元素 
// inorder 均出现在 preorder 
// preorder 保证 为二叉树的前序遍历序列 
// inorder 保证 为二叉树的中序遍历序列 
// 
// Related Topics 树 数组 哈希表 分治 二叉树 👍 1618 👎 0


/**
 * 105, 从前序与中序遍历序列构造二叉树
 * @author Aaron Zhu
 * @date 2022-6-2
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal_105{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {

        private int[] preorder;

        private int[] inorder;

        private Map<Integer, Integer> indexMap;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            init(preorder, inorder);
            TreeNode root = help(0, preorder.length-1, 0, inorder.length-1);
            return root;
        }

        private void init(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            this.inorder = inorder;

            indexMap = new HashMap<>();
            for (int i=0; i<inorder.length; i++) {
                indexMap.put(inorder[i], i);
            }
        }

        private TreeNode help(int preStart, int preEnd, int inStart, int inEnd) {
            if( preStart>preEnd || inStart>inEnd ) {
                return null;
            }

            int rootNum = preorder[ preStart ];
            int rootNumIndexInOrder = indexMap.get( rootNum );
            TreeNode root = new TreeNode(rootNum);

            // 左子树
            TreeNode left = help(preStart+1, rootNumIndexInOrder-inStart+preStart, inStart, rootNumIndexInOrder-1);
            root.left = left;
            // 右子树
            TreeNode right = help(rootNumIndexInOrder-inStart+preStart+1, preEnd, rootNumIndexInOrder+1, inEnd);
            root.right = right;

            return root;
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
