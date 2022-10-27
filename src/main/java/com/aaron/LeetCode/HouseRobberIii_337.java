package com.aaron.LeetCode;

import java.util.*;

//小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。 
//
// 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接
//相连的房子在同一天晚上被打劫 ，房屋将自动报警。 
//
// 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: root = [3,2,3,null,3,null,1]
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7 
//
// 示例 2: 
//
// 
//
// 
//输入: root = [3,4,5,1,3,null,1]
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
// 
//
// 
//
// 提示： 
//
// 
//
// 
// 树的节点数在 [1, 10⁴] 范围内 
// 0 <= Node.val <= 10⁴ 
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1453 👎 0


/**
 * 337, 打家劫舍 III
 * @author Aaron Zhu
 * @date 2022-10-10
 */
public class HouseRobberIii_337{
    
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
    }

    public static class Solution2 {

        private Map<TreeNode, Integer> cache;

        public int rob(TreeNode root) {
            int[] res = calcMaxBalance(root);
            return Math.max(res[0], res[1]);
        }

        /**
         *
         * @param node
         * @return index: 0, 表示不偷; index: 1, 表示偷
         */
        private int[] calcMaxBalance(TreeNode node) {
            if( node == null ) {
                return new int[2];
            }

            int[] left = calcMaxBalance( node.left );
            int[] right = calcMaxBalance( node.right );

            // 不偷当前节点
            int ans1 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            // 偷当前节点
            int ans2 = node.val + left[0] + right[0];
            int[] res = new int[]{ans1, ans2};
            return res;
        }
    }

    public static class Solution1 {
        private Map<TreeNode, Integer> cache;

        public int rob(TreeNode root) {
            cache = new HashMap<>();
            return calcMaxBalance(root);
        }

        private int calcMaxBalance(TreeNode node) {
            if( node == null ) {
                return 0;
            } else if( cache.containsKey(node) ) {
                return cache.get( node );
            }

            // 不偷当前节点
            int ans1 = calcMaxBalance(node.left) + calcMaxBalance(node.right);

            // 偷当前节点
            int ans2 = node.val;
            if( node.left!=null ) {
                ans2 += calcMaxBalance( node.left.left ) + calcMaxBalance( node.left.right );
            }
            if( node.right!=null ) {
                ans2 += calcMaxBalance( node.right.left ) + calcMaxBalance( node.right.right);
            }

            int res = Math.max(ans1, ans2);
            cache.put( node, res );
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
