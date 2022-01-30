package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：
// 对于有根树 T 的两个结点 p、q，
// 最近公共祖先表示为一个结点 x，
// 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）
//
// 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5] 
//
// 示例 1: 
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
// 输出: 6
// 解释: 节点 2 和节点 8 的最近公共祖先是 6。
// 
// 示例 2:
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
// 输出: 2
// 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
//
// 说明: 
// 所有节点的值都是唯一的。
// p、q 为不同节点且均存在于给定的二叉搜索树中。 
//
// 注意：本题与主站 235 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 
// 👍 202 👎 0


/**
 * 剑指 Offer 68 - I, 二叉搜索树的最近公共祖先
 * @author Aaron Zhu
 * @date 2022-1-30
 */
public class Offer_68_1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * DFS
     */
    public static class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode result = null;
            while (true) {
                if( p.val<root.val && q.val<root.val ) {
                    root = root.left;
                } else if( p.val>root.val && q.val>root.val ) {
                    root = root.right;
                } else {
                    result = root;
                    break;
                }
            }

            return result;
        }

        private List<TreeNode> getPath(TreeNode node, TreeNode target) {
            List<TreeNode> path = new ArrayList<>();
            while ( target.val != node.val ) {
                path.add( node );
                if( target.val< node.val ) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            path.add( node );
            return path;
        }
    }

    /**
     * DFS
     */
    public static class Solution2 {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            List<TreeNode> pPath = getPath(root, p);
            List<TreeNode> qPath = getPath(root, q);

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

        private List<TreeNode> getPath(TreeNode node, TreeNode target) {
            List<TreeNode> path = new ArrayList<>();
            while ( target.val != node.val ) {
                path.add( node );
                if( target.val< node.val ) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            path.add( node );
            return path;
        }
    }

    /**
     * BFS
     */
    public static class Solution1 {
        /**
         * key: 子节点的值; value: 父节点的值
         */
        private Map<Integer, Integer> map;

        /**
         * 从root节点到p节点的路径
         */
        private LinkedList<Integer> pList;

        /**
         * 从root节点到q节点的路径
         */
        private LinkedList<Integer> qList;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            buildMap(root, p, q);
            buildPQList(p, q);
            int result = findPublicParent();
            return new TreeNode(result);

        }

        private void buildMap(TreeNode root, TreeNode p, TreeNode q) {
            map = new HashMap<>();
            int num = 0;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer( root );
            while ( !queue.isEmpty() ) {
                TreeNode node = queue.poll();
                Integer value = node.val;
                if( value==p.val || value==q.val ) {
                    num++;
                    // 剪枝: 找到p、q两个节点即结束BFS
                    if( num==2 ) {
                        break;
                    }
                }

                if( node.left!=null ) {
                    map.put(node.left.val, value);
                    queue.add( node.left );
                }

                if( node.right!=null ) {
                    map.put(node.right.val, value);
                    queue.add( node.right );
                }
            }
        }

        private void buildPQList(TreeNode p, TreeNode q) {
            pList = new LinkedList<>();
            pList.addFirst( p.val );
            Integer childValue = p.val;
            while ( map.containsKey(childValue) ) {
                Integer parentValue = map.get( childValue );
                pList.addFirst( parentValue );
                childValue = parentValue;
            }

            qList = new LinkedList<>();
            qList.addFirst( q.val );
            childValue = q.val;
            while ( map.containsKey(childValue) ) {
                Integer parentValue = map.get( childValue );
                qList.addFirst( parentValue );
                childValue = parentValue;
            }
        }

        private int findPublicParent() {
            int lastValue = 0;
            for(int i=0; i<pList.size() && i<qList.size(); i++) {
                if( pList.get(i) == qList.get(i) ) {
                    lastValue = pList.get(i);
                } else {
                    break;
                }
            }
            return lastValue;
        }

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

