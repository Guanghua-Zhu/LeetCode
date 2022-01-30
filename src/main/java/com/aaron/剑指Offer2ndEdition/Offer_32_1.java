package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。 
//
// 例如: 
// 给定二叉树: [3,9,20,null,null,15,7]
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 返回：
// [3,9,20,15,7]
//
// 提示：
// 节点总数 <= 1000
// Related Topics 树 广度优先搜索 二叉树 
// 👍 157 👎 0


/**
 * 面试题32 - I, 从上到下打印二叉树
 * @author Aaron Zhu
 * @date 2022-1-30
 */
public class Offer_32_1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int[] levelOrder(TreeNode root) {
            List<Integer> result = new LinkedList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if( node==null ) {
                    continue;
                }
                result.add( node.val );
                queue.add( node.left );
                queue.add( node.right );
            }

            int[] array = new int[ result.size() ];
            int index = 0;
            for(Integer num : result) {
                array[index] = num;
                index++;
            }
            return array;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

