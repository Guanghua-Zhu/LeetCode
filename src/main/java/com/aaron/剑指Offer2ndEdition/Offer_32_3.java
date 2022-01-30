package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 请实现一个函数按照之字形顺序打印二叉树
// 即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印
// 其他行以此类推
// 例如: 给定二叉树: [3,9,20,null,null,15,7],
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
// 返回其层次遍历结果：
// [
//  [3],
//  [20,9],
//  [15,7]
//]
// 提示：
// 节点总数 <= 1000
// Related Topics 树 广度优先搜索 二叉树
// 👍 173 👎 0

/**
 * 剑指 Offer 32 - III, 从上到下打印二叉树 III
 * @author Aaron Zhu
 * @date 2022-1-30
 */
public class Offer_32_3 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            if( root==null ) {
                return Collections.emptyList();
            }

            List<List<Integer>> result = new LinkedList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            boolean sortFlag = true;

            while (!queue.isEmpty()) {
                LinkedList<Integer> subResult = new LinkedList<>();
                for(int i=queue.size(); i>0; i--) {
                    TreeNode node = queue.poll();
                    if(node.left != null) {
                        queue.add( node.left );
                    }
                    if(node.right != null) {
                        queue.add( node.right );
                    }

                    if( sortFlag ) {
                        subResult.addLast( node.val );
                    } else {
                        subResult.addFirst( node.val );
                    }
                }
                result.add( subResult );
                sortFlag = !sortFlag;
            }
            return result;
        }
    }

    public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
}

