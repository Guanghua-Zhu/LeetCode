package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//请实现两个函数，分别用来序列化和反序列化二叉树。 
//
// 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字
//符串反序列化为原始的树结构。 
//
// 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方
//法解决这个问题。 
//
// 
//
// 示例： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-b
//inary-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 
// 👍 259 👎 0


/**
 * 剑指 Offer 37, 序列化二叉树
 * @author Aaron Zhu
 * @date 2022-1-29
 */

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

public class Offer_37 {
    public static void main(String[] args) {
    }

    /**
     * DFS
     */
    public static class Codec {
        private final static String EMPTY_NODE = "null";

        private final static String SEPARATOR = ",";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root==null) {
                return null;
            }

            StringBuilder sb = new StringBuilder();
            tree2str(root, sb);
            sb.deleteCharAt( sb.length()-1 );
            return sb.toString();
        }

        private void tree2str(TreeNode node, StringBuilder sb) {
            if( node==null ) {
                sb.append(EMPTY_NODE);
                sb.append(SEPARATOR);
                return;
            }

            sb.append( node.val );
            sb.append( SEPARATOR );

            tree2str(node.left, sb);
            tree2str(node.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if( data==null ) {
                return null;
            }

            String[] array = data.split(SEPARATOR);
            List<String> dataList = new LinkedList<>( Arrays.asList(array) );
            return str2Tree(dataList);
        }

        private TreeNode str2Tree(List<String> dataList) {
            if( dataList.get(0).equals( EMPTY_NODE ) ) {
                dataList.remove(0);
                return null;
            }

            TreeNode root = new TreeNode( Integer.valueOf(dataList.get(0)) );
            dataList.remove(0);
            root.left = str2Tree( dataList );
            root.right = str2Tree( dataList );
            return root;
        }
    }

    /**
     * BFS
     */
    public static class Codec1 {

        private final static Integer EMPTY_NODE = null;

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root==null) {
                return null;
            }
            List<Integer> result = new LinkedList();

            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer( root );
            while ( !queue.isEmpty() ) {
                TreeNode treeNode = queue.poll();
                if( treeNode==null ) {
                    result.add( EMPTY_NODE );
                } else {
                    result.add( treeNode.val );
                    queue.add( treeNode.left );
                    queue.add( treeNode.right );
                }
            }

            return result.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if( data==null ) {
                return null;
            }

            data = data.substring(1, data.length()-1 );
            String[] array = data.split(", ");

            LinkedList<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode( Integer.valueOf( array[0] ) );
            queue.offer(root);

            int index = 1;
            while (!queue.isEmpty()) {
                TreeNode treeNode = queue.poll();
                if( treeNode==null ) {
                    continue;
                }

                if( !array[index].equals( EMPTY_NODE ) ) {
                    treeNode.left = new TreeNode( Integer.valueOf(array[index]) );
                }

                if( !array[index+1].equals( EMPTY_NODE ) ) {
                    treeNode.right = new TreeNode( Integer.valueOf(array[index+1]) );
                }

                queue.offer( treeNode.left );
                queue.offer( treeNode.right );
                index += 2;
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

}

