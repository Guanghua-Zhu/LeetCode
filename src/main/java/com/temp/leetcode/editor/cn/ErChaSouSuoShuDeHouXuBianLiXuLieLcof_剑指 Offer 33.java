package com.temp.leetcode.editor.cn;

import java.util.*;

// 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果
// 如果是则返回 true，否则返回 false
// 假设输入的数组的任意两个数字都互不相同
//
// 参考以下这颗二叉搜索树： 
//
//     5
//    / \
//   2   6
//  / \
// 1   3 
//
// 示例 1： 
//
// 输入: [1,6,3,2,5]
//输出: false 
//
// 示例 2： 
//
// 输入: [1,3,2,6,5]
//输出: true 
//
// 
//
// 提示： 
//
// 
// 数组长度 <= 1000 
// 
// Related Topics 栈 树 二叉搜索树 递归 二叉树 单调栈 
// 👍 424 👎 0


/**
 * 剑指 Offer 33, 二叉搜索树的后序遍历序列
 * @author Aaron Zhu
 * @date 2022-2-8
 */
public class ErChaSouSuoShuDeHouXuBianLiXuLieLcof_剑指 Offer 33{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        if( postorder==null ) {
            return true;
        }
        boolean result = verify(postorder, 0, postorder.length-1);
        return result;
    }

    private boolean verify(int[] postorder, int start, int end) {
        if( start>=end ) {
            return true;
        }

        int i = start;
        while ( i<end && postorder[i]<postorder[end] ) {
            i++;
        }

        int spiltPoint = i;

        while ( i<end && postorder[i]>postorder[end] ) {
            i++;
        }

        if( i != end ) {
            return false;
        }

        return verify(postorder, start, spiltPoint-1) && verify(postorder, spiltPoint, end-1);
    }

}
//leetcode submit region end(Prohibit modification and deletion)

class Solution1 {
    public boolean verifyPostorder(int[] postorder) {
        if( postorder==null ) {
            return true;
        }
        boolean result = verify(postorder, 0, postorder.length-1);
        return result;
    }

    private boolean verify(int[] postorder, int start, int end) {
        int size = end-start+1;
        if( size <= 1 ) {
            return true;
        }

        int root = postorder[ end ];

        // 左子树索引范围: [start, leftSubTreeIndex)  , 数量: leftSubTreeIndex - start
        int leftSubTreeIndex = start;
        while (  leftSubTreeIndex < end ) {
            if( postorder[leftSubTreeIndex] > root ) {
                break;
            }
            leftSubTreeIndex++;
        }
        int leftSubTreeSize = leftSubTreeIndex - start;

        // 右子树索引范围: (rightSubTreeIndex, end-1] , 数量: end-1-rightSubTreeIndex
        int rightSubTreeIndex = end-1;
        while (  rightSubTreeIndex >= start ) {
            if( postorder[rightSubTreeIndex] < root ) {
                break;
            }
            rightSubTreeIndex--;
        }
        int rightSubTreeSize = end-1-rightSubTreeIndex;

        if( leftSubTreeSize+rightSubTreeSize < size-1 ) {
            return false;
        }

        // 递归判断左子树、右子树
        return verify(postorder, start, leftSubTreeIndex-1) && verify(postorder, rightSubTreeIndex+1, end-1);
    }

}
