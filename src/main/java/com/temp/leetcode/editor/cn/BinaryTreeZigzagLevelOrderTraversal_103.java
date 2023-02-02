package com.temp.leetcode.editor.cn;

import sun.reflect.generics.tree.Tree;

import java.util.*;

//给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[20,9],[15,7]]
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
// -100 <= Node.val <= 100 
// 
//
// Related Topics 树 广度优先搜索 二叉树 👍 733 👎 0


/**
 * 103, 二叉树的锯齿形层序遍历
 * @author Aaron Zhu
 * @date 2023-2-2
 */
public class BinaryTreeZigzagLevelOrderTraversal_103{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) {
            return res;
        }

        Deque<Node> queue = new LinkedList<>();

        queue.add( new Node(root, 1) );
        int lastLevel = 1;
        List<Integer> temp = new ArrayList<>();
        boolean flag = false;   // true: 从左到右; false: 从右到左

        while ( !queue.isEmpty() ) {
            Node node = queue.pollFirst();
            int currentLevel = node.getLevel();
            if( currentLevel!=lastLevel ) {
                flag = !flag;
                res.add( temp );
                temp = new ArrayList<>();
            }
            temp.add( node.val );


            if( flag ) {
                if( node.left!=null ) {
                    queue.offerLast( new Node(node.left, currentLevel+1)  );
                }
                if( node.right!=null ) {
                    queue.offerLast( new Node(node.right, currentLevel+1)  );
                }
            } else {
                if( node.right!=null ) {
                    queue.offerLast( new Node(node.right, currentLevel+1)  );
                }
                if( node.left!=null ) {
                    queue.offerLast( new Node(node.left, currentLevel+1)  );
                }
            }
        }

        if( temp.size()!=0 ) {
            res.add(temp);
        }
        return res;
    }

    public static class Node extends TreeNode {
        private int level;

        public int getLevel() {
            return level;
        }



        public Node(TreeNode treeNode) {
            this.val = treeNode.val;
            this.left = treeNode.left;
            this.right = treeNode.right;
        }

        public Node(TreeNode treeNode, int level) {
            this.val = treeNode.val;
            this.left = treeNode.left;
            this.right = treeNode.right;
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