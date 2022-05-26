package com.temp.leetcode.editor.cn;

import java.util.*;

//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。 
//
// 有效 二叉搜索树定义如下： 
//
// 
// 节点的左子树只包含 小于 当前节点的数。 
// 节点的右子树只包含 大于 当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [2,1,3]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围在[1, 10⁴] 内 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1595 👎 0


/**
 * 98, 验证二叉搜索树
 * @author Aaron Zhu
 * @date 2022-5-25
 */
public class ValidateBinarySearchTree_98{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    public boolean isValidBST(TreeNode root) {

    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution2 {
    public boolean isValidBST(TreeNode root) {
        boolean res = true;

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        Long lastVal = Long.MIN_VALUE;

        while ( cur!=null || !stack.isEmpty() ) {
            while ( cur!=null ) {
                stack.push( cur );
                cur  = cur.left;
            }

            cur = stack.poll();

            Long currentVal = (long) cur.val;
            if( lastVal >= currentVal ) {
                res = false;
                break;
            }
            lastVal = currentVal;

            cur = cur.right;
        }

        return res;
    }
}

/**
 * 先(迭代)中序遍历 再比较
 */
class Solution1 {
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = inorderTraversal(root);
        boolean res = true;
        for (int i=0; i<list.size()-1; i++) {
            if( list.get(i) >= list.get(i+1) ) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * 基于迭代的中序遍历
     * @param root
     * @return
     */
    private List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;

        while ( cur!=null || !stack.isEmpty() ) {
            while ( cur!=null ) {
                stack.push( cur );
                cur  = cur.left;
            }

            cur = stack.poll();
            list.add( cur.val );
            cur = cur.right;
        }

        return list;
    }
}

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
