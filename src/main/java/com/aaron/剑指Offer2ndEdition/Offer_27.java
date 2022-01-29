package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//请完成一个函数，输入一个二叉树，该函数输出它的镜像。 
//
// 例如输入： 
//
//    4
//   / \
//  2   7
// / \ / \
// 1 3 6 9
//镜像输出： 
//
//    4
//   / \
//  7   2
// / \ / \ 
// 9 6 3 1
//
// 
//
// 示例 1： 
//
// 输入：root = [4,2,7,1,3,6,9]
//输出：[4,7,2,9,6,3,1]
// 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 1000 
//
// 注意：本题与主站 226 题相同：https://leetcode-cn.com/problems/invert-binary-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 204 👎 0


/**
 * 剑指 Offer 27, 二叉树的镜像
 * @author Aaron Zhu
 * @date 2022-1-28
 */
public class Offer_27 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * BFS
     */
    public static class Solution {
        public TreeNode mirrorTree(TreeNode root) {
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while ( !queue.isEmpty() ) {
                TreeNode node = queue.poll();
                if( node==null ) {
                    continue;
                }

                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;

                queue.add( node.left );
                queue.add( node.right );
            }

            return root;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * DFS
     */
    public static class Solution1 {
        public TreeNode mirrorTree(TreeNode root) {
            swap(root);
            return root;
        }

        public void swap(TreeNode node) {
            if( node==null ) {
                return;
            }

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            swap( node.left );
            swap( node.right );
        }
    }
}

