package com.aaron.LeetCode;

import java.util.*;

//给你二叉树的根结点 root ，请你将它展开为一个单链表： 
//
// 
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。 
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？ 
// Related Topics 栈 树 深度优先搜索 链表 二叉树 👍 1195 👎 0


/**
 * 114, 二叉树展开为链表
 * @author Aaron Zhu
 * @date 2022-6-4
 */
public class FlattenBinaryTreeToLinkedList_114{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public void flatten(TreeNode root) {
            TreeNode cur = root;
            while ( cur!=null ) {
                if( cur.left != null ) {
                    TreeNode mostRightNodeInLeftSubTree = cur.left;
                    while ( mostRightNodeInLeftSubTree.right!=null ) {
                        mostRightNodeInLeftSubTree = mostRightNodeInLeftSubTree.right;
                    }
                    // 先暂存右子树
                    mostRightNodeInLeftSubTree.right = cur.right;
                    // 把左子树移动到右子树
                    cur.right = cur.left;
                    // 左指针置空
                    cur.left = null;
                }

                cur = cur.right;
            }
        }
    }

    public static class Solution1 {
        public void flatten(TreeNode root) {
            List<TreeNode> list = new ArrayList<>();
            preOrder(root, list);

            for (int i=0; i<list.size()-1; i++) {
                TreeNode cur = list.get(i);
                TreeNode next = list.get(i+1);
                cur.left = null;
                cur.right = next;
            }
        }

        private void preOrder(TreeNode cur, List<TreeNode> list) {
            if( cur==null ) {
                return;
            }

            list.add( cur );
            preOrder(cur.left, list);
            preOrder(cur.right, list);
        }
    }

    public static  class TreeNode {
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