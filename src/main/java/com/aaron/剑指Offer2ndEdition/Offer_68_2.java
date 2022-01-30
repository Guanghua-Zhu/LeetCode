package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先
//
// 百度百科中最近公共祖先的定义为
// 对于有根树 T 的两个结点 p、q
// 最近公共祖先表示为一个结点 x
// 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
// 示例 1:
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
// 输出: 3
// 解释: 节点 5 和节点 1 的最近公共祖先是节点 3
//
// 示例 2:
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
// 输出: 5
// 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身
//
// 说明:
// 所有节点的值都是唯一的
// p、q 为不同节点且均存在于给定的二叉树中
//
// 注意：本题与主站 236 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
// Related Topics 树 深度优先搜索 二叉树 
// 👍 368 👎 0

/**
 * 剑指 Offer 68 - II, 二叉树的最近公共祖先
 * @author Aaron Zhu
 * @date 2022-1-30
 */
public class Offer_68_2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            LinkedList<TreeNode> pPath = new LinkedList<>();
            LinkedList<TreeNode> qPath = new LinkedList<>();
            getPath(root, p, pPath );
            getPath(root, q, qPath );

            TreeNode result = null;
            for(int i=0; i<pPath.size() && i<qPath.size(); i++) {
                if( pPath.get(i)==qPath.get(i) ) {
                    result = pPath.get(i);
                } else {
                    break;
                }
            }
            return result;
        }

        private boolean getPath(TreeNode node, TreeNode target, LinkedList<TreeNode> path) {
            path.addLast(node);
            if(node.val==target.val) {
                return true;
            }

            if( node.left!=null && getPath(node.left, target, path) ) {
                return true;
            }

            if( node.right!=null && getPath(node.right, target, path) ) {
                return true;
            }

            path.removeLast();
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

