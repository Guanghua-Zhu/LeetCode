package com.aaron.LeetCode;

import java.util.*;

//给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[3,2,1]
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
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 827 👎 0


/**
 * 145, 二叉树的后序遍历
 * @author Aaron Zhu
 * @date 2022-5-3
 */
public class BinaryTreePostorderTraversal_145{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 基于迭代的后序遍历
     */
    public static class Solution {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            LinkedList<TreeNode> stack = new LinkedList<>();

            // 先按 根、右、左 的顺序进行遍历
            while ( root!=null || !stack.isEmpty() ) {
                while (root!=null) {
                    // 先处理当前节点
                    res.add( root.val );
                    // 然后沿右子树一直入栈
                    stack.addLast( root );
                    root = root.right;
                }

                // 右子树遍历完毕, 通过父节点获取左子树再进行处理
                TreeNode parent = stack.removeLast();
                root = parent.left;
            }

            // 对遍历结果进行翻转
            // 这样遍历结果就由 根、右、左 就变为 左、右、根, 即后序遍历
            Collections.reverse( res );
            return res;
        }
    }

    /**
     * 基于递归的后序遍历
     */
    public static class Solution1 {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> list = new LinkedList<>();
            dfs(root, list);
            return list;
        }

        private void dfs(TreeNode cur, List<Integer> list) {
            if( cur==null ) {
                return;
            }

            // 1. 处理左子树
            dfs(cur.left, list);

            // 2. 处理右子树
            dfs(cur.right, list);

            // 3. 处理当前节点
            list.add( cur.val );
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
