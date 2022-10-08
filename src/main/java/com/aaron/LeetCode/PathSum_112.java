package com.aaron.LeetCode;

import java.util.*;

//给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和
// targetSum 。如果存在，返回 true ；否则，返回 false 。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
//输出：true
//解释：等于目标和的根节点到叶节点路径如上图所示。
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,2,3], targetSum = 5
//输出：false
//解释：树中存在两条根节点到叶子节点的路径：
//(1 --> 2): 和为 3
//(1 --> 3): 和为 4
//不存在 sum = 5 的根节点到叶子节点的路径。 
//
// 示例 3： 
//
// 
//输入：root = [], targetSum = 0
//输出：false
//解释：由于树是空的，所以不存在根节点到叶子节点的路径。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1006 👎 0


/**
 * 112, 路径总和
 * @author Aaron Zhu
 * @date 2022-9-24
 */
public class PathSum_112{
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node8 = new TreeNode(8);
        TreeNode node11 = new TreeNode(11);
        TreeNode node13 = new TreeNode(13);
        TreeNode node4b = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node5b = new TreeNode(5);

        node5.left =  node4;
        node5.right = node8;
        node4.left = node11;
        node8.left = node13;
        node8.right = node4b;
        node11.left = node7;
        node11.right = node2;
        node4b.right = node1;
        node4b.left = node5b;

        solution.hasPathSum(node5, 22);
        System.out.println("gg");
    }

    /**
     * 回溯
     */
    public static class Solution2 {

        private int sum;

        private int targetSum;

        private boolean res;


        public boolean hasPathSum(TreeNode root, int targetSum) {
            if( root==null ) {
                return false;
            }

            init(root, targetSum );
            dfs( root );
            return res;
        }

        private void init(TreeNode root, int targetSum) {
            sum = root.val;
            this.targetSum = targetSum;
            res = false;
        }

        private void dfs(TreeNode node) {
            // 剪枝
            if( res ) {
                return;
            }

            if( node.left==null && node.right==null ) {
                if( this.sum == this.targetSum ) {
                    res = true;
                }
                return;
            }

            if( node.left != null ) {
                sum += node.left.val;
                dfs( node.left );
                sum -= node.left.val;
            }

            if( node.right != null ) {
                sum += node.right.val;
                dfs( node.right );
                sum -= node.right.val;
            }
        }
    }

    /**
     * BFS
     */
    public static class Solution1 {
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if( root==null ) {
                return false;
            }

            boolean res = false;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add( root );
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if( node.left==null && node.right==null ) {
                    if( node.val==targetSum ) {
                        res = true;
                        break;
                    }
                } else {
                    if (node.left!=null) {
                        node.left.val = node.left.val + node.val;
                        queue.offer( node.left );
                    }

                    if (node.right != null) {
                        node.right.val = node.right.val + node.val;
                        queue.offer( node.right );
                    }
                }
            }

            return res;
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