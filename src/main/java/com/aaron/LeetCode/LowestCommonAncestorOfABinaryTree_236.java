package com.aaron.LeetCode;

import java.util.*;

//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], p = 1, q = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 10⁵] 内。 
// -10⁹ <= Node.val <= 10⁹ 
// 所有 Node.val 互不相同 。 
// p != q 
// p 和 q 均存在于给定的二叉树中。 
// 
// Related Topics 树 深度优先搜索 二叉树 👍 1825 👎 0


/**
 * 236, 二叉树的最近公共祖先
 * @author Aaron Zhu
 * @date 2022-7-4
 */
public class LowestCommonAncestorOfABinaryTree_236{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);

        node3.left = node5;
        node3.right = node1;
        node5.left = node6;
        node5.right = node2;
        node1.left = node0;
        node1.right = node8;
        node2.left = node7;
        node2.right = node4;

        solution.lowestCommonAncestor(node3, node5, node4);
        System.out.println("gg");
    }

    public static class Solution {
        private TreeNode res;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            res = null;
            search(root, p,q);
            return res;
        }

        private boolean search(TreeNode cur, TreeNode p, TreeNode q) {
            if( res!=null || cur==null ) {
                return false;
            }

            boolean b1 =  search(cur.left, p, q);
            boolean b2 =  search(cur.right, p, q);

            if( res!=null ) {
                return false;
            }

            if( b1==true && b2==true ) {
                res = cur;
                return false;
            } else if( b1==false && b2==false ) {
                if( p.val== cur.val || q.val==cur.val ) {
                    return true;
                } else {
                    return false;
                }
            } else if( b1==true || b2==true ) {
                if( p.val== cur.val || q.val==cur.val ) {
                    res = cur;
                }
                return true;
            }

            return false;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}