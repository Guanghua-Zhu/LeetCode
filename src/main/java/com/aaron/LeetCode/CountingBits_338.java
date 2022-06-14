package com.aaron.LeetCode;

import java.util.*;

//给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。 
//
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：n = 2
//输出：[0,1,1]
//解释：
//0 --> 0
//1 --> 1
//2 --> 10
// 
//
// 示例 2： 
//
// 
//输入：n = 5
//输出：[0,1,1,2,1,2]
//解释：
//0 --> 0
//1 --> 1
//2 --> 10
//3 --> 11
//4 --> 100
//5 --> 101
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 10⁵ 
// 
//
// 
//
// 进阶： 
//
// 
// 很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？ 
// 你能不使用任何内置函数解决此问题吗？（如，C++ 中的 __builtin_popcount ） 
// 
// 
// 
// Related Topics 位运算 动态规划 👍 1015 👎 0


/**
 * 338, 比特位计数
 * @author Aaron Zhu
 * @date 2022-6-14
 */
public class CountingBits_338{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * DP
     */
    public static class Solution {
        public int[] countBits(int n) {
            int[] res = new int[n+1];
            for (int i=1; i<=n; i++) {
                if( i%2==0 ) {  // 偶数
                    res[i] = res[i/2];
                } else {        // 奇数
                    res[i] = res[i-1] + 1;
                }
            }
            return res;
        }
    }

    public static class Solution1 {
        public int[] countBits(int n) {
            int[] res = new int[n+1];
            for (int i=0; i<=n; i++) {
                res[i] = countOne(i);
            }
            return res;
        }

        private int countOne(int x) {
            int count = 0;
            while ( x!=0 ) {
                count += x & 1;
                x = x >> 1;
            }
            return count;
        }
    }
}
