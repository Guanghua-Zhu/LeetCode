package com.aaron.LeetCode;

import java.util.*;

//两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。 
//
// 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 1, y = 4
//输出：2
//解释：
//1   (0 0 0 1)
//4   (0 1 0 0)
//       ↑   ↑
//上面的箭头指出了对应二进制位不同的位置。
// 
//
// 示例 2： 
//
// 
//输入：x = 3, y = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 0 <= x, y <= 2³¹ - 1 
// 
// Related Topics 位运算 👍 605 👎 0


/**
 * 461, 汉明距离
 * @author Aaron Zhu
 * @date 2022-6-14
 */
public class HammingDistance_461{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int hammingDistance(int x, int y) {
            long z = x ^ y;
            return Long.bitCount( z );
        }
    }

    public static class Solution1 {
        public int hammingDistance(int x, int y) {
            int count = 0;
            long z = x ^ y;
            while ( z!=0 ) {
                count += z & 1;
                z = z >> 1;
            }
            return count;
        }
    }
}
