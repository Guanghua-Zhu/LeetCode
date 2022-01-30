package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7]
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 返回其层次遍历结果： 
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 提示：
// 节点总数 <= 1000
// 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
// Related Topics 树 广度优先搜索 二叉树 
// 👍 174 👎 0


/**
 * 剑指 Offer 32 - II, 从上到下打印二叉树 II
 * @author Aaron Zhu
 * @date 2022-1-30
 */
public class Offer_32_2 {
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
            queue.add( root );

            while ( !queue.isEmpty() ) {
                List<Integer> subResult = new LinkedList<>();
                for(int i=queue.size(); i>0; i--) {
                    TreeNode treeNode = queue.poll();

                    subResult.add( treeNode.val );
                    if(treeNode.left != null) {
                        queue.add( treeNode.left );
                    }
                    if(treeNode.right !=null)  {
                        queue.add( treeNode.right );
                    }
                }
                result.add( subResult );
            }
            return result;
        }
    }

    public static class Solution1 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new LinkedList<>();
            List<Integer> subResult = new LinkedList<>();
            if( root==null ) {
                return result;
            }

            Queue<TreeNode> treeNodeQueue = new LinkedList<>();
            treeNodeQueue.add( root );

            Queue<Integer> levelQueue = new LinkedList<>();
            levelQueue.add( 1 );

            Integer lastLevel = 1;
            while ( !treeNodeQueue.isEmpty() ) {
                TreeNode treeNode = treeNodeQueue.poll();
                Integer level = levelQueue.poll();

                if( treeNode==null ) {
                    continue;
                }

                // 开始新的一层
                if( lastLevel != level ) {
                    lastLevel = level;
                    result.add( new ArrayList<>(subResult) );
                    subResult.clear();
                }

                subResult.add( treeNode.val );
                treeNodeQueue.add( treeNode.left );
                treeNodeQueue.add( treeNode.right );
                levelQueue.add( level+1 );
                levelQueue.add( level+1 );
            }
            result.add( new ArrayList<>(subResult) );
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

