package com.aaron.LeetCode;

import java.util.*;

//给你两棵二叉树： root1 和 root2 。 
//
// 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠
//，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。 
//
// 返回合并后的二叉树。 
//
// 注意: 合并过程必须从两个树的根节点开始。 
//
// 
//
// 示例 1： 
//
// 
//输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
//输出：[3,4,5,5,4,null,7]
// 
//
// 示例 2： 
//
// 
//输入：root1 = [1], root2 = [1,2]
//输出：[2,2]
// 
//
// 
//
// 提示： 
//
// 
// 两棵树中的节点数目在范围 [0, 2000] 内 
// -10⁴ <= Node.val <= 10⁴ 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 996 👎 0


/**
 * 617, 合并二叉树
 * @author Aaron Zhu
 * @date 2022-6-16
 */
public class MergeTwoBinaryTrees_617{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            TreeNode res = merge(root1, root2);
            return res;
        }

        private TreeNode merge(TreeNode tree1, TreeNode tree2) {
            if( tree1==null && tree2==null ) {
                return null;
            }

            TreeNode mergeNode = null;
            if( tree1==null ) {
                mergeNode = new TreeNode(tree2.val );
                mergeNode.left = merge(null, tree2.left);
                mergeNode.right = merge(null, tree2.right);
            } else if( tree2==null ) {
                mergeNode = new TreeNode(tree1.val );
                mergeNode.left = merge(tree1.left, null);
                mergeNode.right = merge(tree1.right, null);
            } else {
                mergeNode = new TreeNode(tree1.val+ tree2.val );
                mergeNode.left = merge(tree1.left, tree2.left);
                mergeNode.right = merge(tree1.right, tree2.right);
            }

            return mergeNode;
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

