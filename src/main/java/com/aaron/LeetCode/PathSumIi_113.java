package com.aaron.LeetCode;

import java.util.*;

//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
// 
// 
// 
// 
//
// 示例 1： 
// 
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 
//
// 示例 2： 
// 
// 
//输入：root = [1,2,3], targetSum = 5
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点总数在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
//
// Related Topics 树 深度优先搜索 回溯 二叉树 👍 843 👎 0


/**
 * 113, 路径总和 II
 * @author Aaron Zhu
 * @date 2022-9-24
 */
public class PathSumIi_113{
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

        solution.pathSum(node5, 22);
        System.out.println("gg");
    }

    /**
     * 回溯
     */
    public static class Solution1 {
        private List<List<Integer>> res;

        private List<Integer> state;

        private Integer sum;

        private int target;

        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            if( root==null ) {
                return Collections.emptyList();
            }
            init(root, targetSum);
            dfs(root);
            return res;
        }

        private void init(TreeNode root, int target) {
            res = new ArrayList<>();
            state = new ArrayList<>();
            this.target = target;
            state.add( root.val );
            sum = root.val;
        }

        private void dfs(TreeNode node) {
            if( node==null ) {
                return;
            }

            if( node.left==null && node.right==null ) {
                if( sum == target ) {
                    res.add( new ArrayList<>(state) );
                }
            } else {
                if( node.left != null ) {
                    state.add( node.left.val );
                    sum += node.left.val;
                    dfs( node.left );
                    state.remove( state.size()-1 );
                    sum -= node.left.val;
                }

                if( node.right != null ) {
                    state.add( node.right.val );
                    sum += node.right.val;
                    dfs( node.right );
                    state.remove( state.size()-1 );
                    sum -= node.right.val;
                }
            }
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