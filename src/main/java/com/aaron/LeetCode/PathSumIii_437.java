package com.aaron.LeetCode;

import java.util.*;

//给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。 
//
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
//输出：3
//解释：和等于 8 的路径有 3 条，如图所示。
// 
//
// 示例 2： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：3
// 
//
// 
//
// 提示: 
//
// 
// 二叉树的节点个数的范围是 [0,1000] 
// 
// -10⁹ <= Node.val <= 10⁹ 
// -1000 <= targetSum <= 1000 
// 
//
// Related Topics 树 深度优先搜索 二叉树 👍 1469 👎 0


/**
 * 437, 路径总和 III
 * @author Aaron Zhu
 * @date 2022-9-24
 */
public class PathSumIii_437{
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        TreeNode node1 = new TreeNode(1000000000);
        TreeNode node2 = new TreeNode(1000000000);
        TreeNode node3 = new TreeNode(294967296);
        TreeNode node4 = new TreeNode(1000000000);
        TreeNode node5 = new TreeNode(1000000000);
        TreeNode node6 = new TreeNode(1000000000);

        node1.left = node2;
        node2.left = node3;
        node3.left = node4;
        node4.left = node5;
        node5.left = node6;
        solution.pathSum(node1, 0);
        System.out.println("gg");
    }

    /**
     * 回溯+前缀和
     */
    public static class Solution2 {
        private int res;

        // 从根节点到当前节点(不含当前节点)路径上的前缀和所出现的次数
        private Map<Long, Integer> prefixMap;

        // 从根节点到当前节点的前缀和
        private long sum;

        private long target;

        public int pathSum(TreeNode root, int targetSum) {
            if( root==null ) {
                return 0;
            }

            init(targetSum);
            dfs(root);
            return res;
        }

        private void init(int target) {
            this.res = 0;
            this.prefixMap = new HashMap<>();
            prefixMap.put(0L, 1);
            this.target = target;
            this.sum = 0;
        }

        private void dfs(TreeNode node) {
            // 剪枝
            if( node==null ) {
                return;
            }

            sum += node.val;

            // 更新搜索结果
            res += prefixMap.getOrDefault(sum-target, 0);

            prefixMap.put(sum, prefixMap.getOrDefault(sum,0)+1 );

            dfs( node.left );
            dfs( node.right );

            prefixMap.put(sum, prefixMap.getOrDefault(sum,0)-1 );
            sum -= node.val;
        }
    }

    /**
     * 回溯
     */
    public static class Solution1 {
        private int res;

        private List<Integer> state;

        private long target;

        public int pathSum(TreeNode root, int targetSum) {
            if( root==null ) {
                return 0;
            }

            init(root, targetSum);
            dfs(root);
            return res;
        }

        private void init(TreeNode root, int target) {
            this.res = 0;
            this.state = new ArrayList<>();
            this.target = target;
            state.add( root.val );
        }

        private void dfs(TreeNode node) {
            // 剪枝
            if( node==null ) {
                return;
            }

            // 搜索是否成功
            long sum = 0L;
            for (int i=state.size()-1; i>=0; i--) {
                sum += state.get(i);
                if( sum == target ) {
                    res++;
                }
            }

            if( node.left != null ) {
                state.add( node.left.val );
                dfs( node.left );
                state.remove( state.size()-1 );
            }

            if( node.right != null ) {
                state.add( node.right.val );
                dfs( node.right );
                state.remove( state.size()-1 );
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

