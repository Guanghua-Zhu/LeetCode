package com.temp.leetcode.editor.cn;

import java.util.*;

//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 10⁴] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 908 👎 0


/**
 * 297, 二叉树的序列化与反序列化
 * @author Aaron Zhu
 * @date 2022-7-6
 */
public class SerializeAndDeserializeBinaryTree_297{
    
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        Codec ser = new Codec();
        String str = ser.serialize( node1 );

        Codec deser = new Codec();
        TreeNode ans = deser.deserialize( str );

        System.out.println("gg");
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * BFS
 */
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if( root==null ) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        sb.append(root.val + ",");
        while ( !queue.isEmpty() ) {
            TreeNode cur = queue.poll();

            if( cur.left!=null ) {
                queue.offer( cur.left );
                sb.append(cur.left.val + ",");
            } else {
                sb.append("NULL,");
            }

            if( cur.right!=null ) {
                queue.offer( cur.right );
                sb.append(cur.right.val + ",");
            } else {
                sb.append("NULL,");
            }
        }

        String res= sb.substring(0, sb.length()-1);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if( data==null ) {
            return null;
        }

        String[] datas = data.split(",");
        TreeNode root = new TreeNode( Integer.parseInt(datas[0]) );
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer( root );
        int dataIndex = 1;
        while ( !queue.isEmpty() ) {
            TreeNode cur = queue.poll();

            Integer leftVal = parseVal( datas[dataIndex] );
            if( leftVal!=null ) {
                TreeNode left = new TreeNode( leftVal );
                cur.left = left;
                queue.offer( left );
            }

            Integer rightVal = parseVal( datas[dataIndex+1] );
            if( rightVal!=null ) {
                TreeNode right = new TreeNode( rightVal );
                cur.right = right;
                queue.offer( right );
            }

            dataIndex += 2;
        }
        return root;
    }

    private Integer parseVal(String val) {
        if ( "NULL".equals( val ) ) {
            return null;
        } else {
           return Integer.parseInt(val);
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

/**
 * BFS
 */
class Codec1 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if( root==null ) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        sb.append(root.val + ",");
        while ( !queue.isEmpty() ) {
            TreeNode cur = queue.poll();

            if( cur.left!=null ) {
                queue.offer( cur.left );
                sb.append(cur.left.val + ",");
            } else {
                sb.append("NULL,");
            }

            if( cur.right!=null ) {
                queue.offer( cur.right );
                sb.append(cur.right.val + ",");
            } else {
                sb.append("NULL,");
            }
        }

        String res= sb.substring(0, sb.length()-1);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if( data==null ) {
            return null;
        }

        String[] datas = data.split(",");
        TreeNode root = new TreeNode( Integer.parseInt(datas[0]) );
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer( root );
        int dataIndex = 1;
        while ( !queue.isEmpty() ) {
            TreeNode cur = queue.poll();

            Integer leftVal = parseVal( datas[dataIndex] );
            if( leftVal!=null ) {
                TreeNode left = new TreeNode( leftVal );
                cur.left = left;
                queue.offer( left );
            }

            Integer rightVal = parseVal( datas[dataIndex+1] );
            if( rightVal!=null ) {
                TreeNode right = new TreeNode( rightVal );
                cur.right = right;
                queue.offer( right );
            }

            dataIndex += 2;
        }
        return root;
    }

    private Integer parseVal(String val) {
        if ( "NULL".equals( val ) ) {
            return null;
        } else {
            return Integer.parseInt(val);
        }
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}