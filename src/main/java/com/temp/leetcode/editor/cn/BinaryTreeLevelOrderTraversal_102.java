package com.temp.leetcode.editor.cn;

import sun.reflect.generics.tree.Tree;

import java.util.*;

//给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[9,20],[15,7]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 1341 👎 0


/**
 * 102, 二叉树的层序遍历
 * @author Aaron Zhu
 * @date 2022-6-1
 */
public class BinaryTreeLevelOrderTraversal_102{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> subRes = new LinkedList<>();

        Queue<TreeNodeInfo> queue = new LinkedList<>();
        queue.offer( new TreeNodeInfo(root, 1) );
        queue.offer( new TreeNodeInfo(root, 1) );
        int currentLevel = 0;
        while ( !queue.isEmpty() ) {
            TreeNodeInfo current = queue.poll();
            int level = current.level;
            if( level>currentLevel ) {
                if( !subRes.isEmpty() ) {
                    res.add( subRes );
                }
                subRes = new LinkedList<>();
            }
            subRes.add( current.val );

            if( current.left != null ) {
                queue.offer( new TreeNodeInfo( current.left, level+1 ) );
            }

            if( current.right != null ) {
                queue.offer( new TreeNodeInfo( current.right, level+1 ) );
            }
        }

        return res;
    }

    public static class TreeNodeInfo extends TreeNode {
        int level;

        public TreeNodeInfo (TreeNode treeNode, int level) {
            super(treeNode.val, treeNode.left, treeNode.right);
            this.level = level;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class TreeNode {
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
