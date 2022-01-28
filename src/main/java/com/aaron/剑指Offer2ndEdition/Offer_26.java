package com.aaron.剑指Offer2ndEdition;

//输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
//
// B是A的子结构， 即 A中有出现和B相同的结构和节点值。 
//
// 例如: 
//给定的树 A: 
//
// 3 
// / \ 
// 4 5 
// / \ 
// 1 2 
//给定的树 B： 
//
// 4 
// / 
// 1 
//返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。 
//
// 示例 1： 
//
// 输入：A = [1,2,3], B = [3,1]
//输出：false
// 
//
// 示例 2： 
//
// 输入：A = [3,4,5,1,2], B = [4,1]
//输出：true 
//
// 限制： 
//
// 0 <= 节点个数 <= 10000 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 442 👎 0


/**
 * 剑指 Offer 26, 树的子结构
 * @author Aaron Zhu
 * @date 2022-1-28
 */
public class Offer_26 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public boolean isSubStructure(TreeNode A, TreeNode B) {
            boolean result = isSub(A, B);
            return result;
        }

        private boolean isSub(TreeNode aTree, TreeNode bTree) {
            if (aTree == null || bTree == null) {
                return false;
            }

            // 将B树与当前节点的子树进行比较
            if (compareTwoTree(aTree, bTree)) {
                return true;
            }

            // 判断B树是否存在于左子树
            if (isSub(aTree.left, bTree)) {
                return true;
            }

            // 判断B树是否存在于右子树
            if (isSub(aTree.right, bTree)) {
                return true;
            }

            return false;
        }

        /**
         * 比较两个树
         *
         * @param aTree
         * @param bTree
         * @return
         */
        private boolean compareTwoTree(TreeNode aTree, TreeNode bTree) {
            if (bTree == null) {
                // 说明B树已经越过叶子节点, 故为true
                return true;
            } else if (aTree == null) {
                // 说明A树已经越过叶子节点, 故为false
                return false;
            } else if (aTree.val != bTree.val) {
                // 两棵树当前节点的值不相同, 直接为false
                return false;
            }

            // 两棵树当前节点比较完, 再分别比较左子树、右子树
            boolean completeEqual = compareTwoTree(aTree.left, bTree.left) && compareTwoTree(aTree.right, bTree.right);
            return completeEqual;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

