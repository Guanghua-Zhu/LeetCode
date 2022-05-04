package com.aaron.LeetCode;

import java.util.*;

//给你二叉树的根节点 root ，返回它节点值的 前序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,2,3]
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
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 809 👎 0


/**
 * 144, 二叉树的前序遍历
 * @author Aaron Zhu
 * @date 2022-5-3
 */
public class BinaryTreePreorderTraversal_144{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }


    /**
     * 基于Morris算法的前序遍历
     */
    public static class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            if( root == null ) {
                return res;
            }

            TreeNode cur = root;
            // 当前节点cur的左子树的最右节点
            TreeNode mostRight = null;

            while ( cur != null ) {
                // 当前节点的左子树为空
                if( cur.left == null ) {
                    // 处理当前节点
                    res.add( cur.val );
                    // 处理当前节点的右子树
                    cur = cur.right;
                } else {    // 当前节点的左子树不为空
                    // 寻找当前节点cur的左子树的最右节点
                    mostRight = cur.left;
                    while ( mostRight.right!=null && mostRight.right!=cur ) {
                        mostRight = mostRight.right;
                    }

                    if( mostRight.right == null) {  // 说明cur节点的左子树未遍历
                        // 处理当前节点
                        res.add( cur.val );
                        // 处理当前节点的左子树
                        mostRight.right = cur;
                        cur = cur.left;
                    } else if ( mostRight.right == cur ) {  // 说明cur节点的左子树已经遍历完成
                        // 处理当前节点的右子树
                        mostRight.right = null;
                        cur = cur.right;
                    }
                }
            }

            return res;
        }
    }


    /**
     * 基于迭代的前序遍历
     */
    public static  class Solution2 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            LinkedList<TreeNode> stack = new LinkedList<>();

            while ( root!=null || !stack.isEmpty() ) {
                while (root!=null) {
                    // 先处理当前节点
                    res.add( root.val );
                    // 然后沿左子树一直入栈
                    stack.addLast( root );
                    root = root.left;
                }

                // 左子树遍历完毕, 通过父节点来对右子树再进行处理
                TreeNode parent = stack.removeLast();
                root = parent.right;
            }

            return res;
        }
    }

    /**
     * 基于递归的前序遍历
     */
    public static class Solution1 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new LinkedList<>();
            dfs(root, list);
            return list;
        }

        private void dfs(TreeNode cur, List<Integer> list) {
            if( cur==null ) {
                return;
            }

            // 1. 处理当前节点
            list.add( cur.val );

            // 2. 处理左子树
            dfs(cur.left, list);

            // 3. 处理右子树
            dfs(cur.right, list);
        }
    }

    /**
     * 树的节点
     */
    public static class TreeNode {
        TreeNode left;

        TreeNode right;

        int val;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
