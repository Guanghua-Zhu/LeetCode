package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点总数在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
//
// 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/ 
// Related Topics 树 深度优先搜索 回溯 二叉树 
// 👍 281 👎 0


/**
 * 剑指 Offer 34, 二叉树中和为某一值的路径
 * @author Aaron Zhu
 * @date 2022-1-28
 */
public class Offer_34 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * BFS
     */
    public static class Solution2 {

        private List<List<Integer>> result;

        /**
         * key: 子节点; value: 父节点
         */
        private Map<TreeNode, TreeNode> map;

        /**
         * 叶子节点列表
         */
        private List<TreeNode> leafNodeList;

        public List<List<Integer>> pathSum(TreeNode root, int target) {
            if( root==null ) {
                return new LinkedList<>();
            }

            init(root);
            bfs(root);
            calcPath(target);
            return result;
        }

        private void init(TreeNode root) {
            result = new LinkedList<>();
            map = new HashMap<>();
            map.put(root, null);
            leafNodeList = new LinkedList<>();
        }

        private void bfs(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add( root );
            while ( !queue.isEmpty() ) {
                TreeNode treeNode = queue.poll();
                // 叶子节点
                if( treeNode.left==null && treeNode.right==null ) {
                    leafNodeList.add( treeNode );
                    continue;
                }

                for(TreeNode subNode : Arrays.asList(treeNode.left, treeNode.right)) {
                    if( subNode==null ) {
                        continue;
                    }
                    map.put(subNode, treeNode);
                    queue.add( subNode );
                }
            }
        }

        private void calcPath(int target) {
            for(TreeNode node : leafNodeList) {
                int sum = 0;
                LinkedList<Integer> path = new LinkedList<>();
                while ( node!=null ) {
                    path.addFirst( node.val );
                    sum += node.val;
                    node = map.get( node );
                }
                if( sum==target ) {
                    result.add( path );
                }
            }
        }

    }

    /**
     * DFS
     */
    public static class Solution {

        private List<List<Integer>> result;

        private LinkedList<Integer> path;

        public List<List<Integer>> pathSum(TreeNode root, int target) {
            init();
            search(root, target);
            return result;
        }

        private void init() {
            result = new LinkedList<>();
            path = new LinkedList<>();
        }

        private void search(TreeNode treeNode, int target) {
            if( treeNode==null ) {
                return;
            }

            path.addLast( treeNode.val );
            // 叶子节点
            if( treeNode.left==null && treeNode.right==null ) {
                Integer sum = path.stream()
                    .mapToInt( Integer::intValue )
                    .sum();
                if( sum == target ) {
                    result.add( new LinkedList<>(path) );
                }
            }
            search( treeNode.left, target);
            search( treeNode.right, target);
            path.removeLast();
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


