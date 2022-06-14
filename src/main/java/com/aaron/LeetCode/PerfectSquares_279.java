package com.aaron.LeetCode;

import java.util.*;

//给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。 
//
// 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：3 
//解释：12 = 4 + 4 + 4 
//
// 示例 2： 
//
// 
//输入：n = 13
//输出：2
//解释：13 = 4 + 9 
// 
//
// 提示： 
//
// 
// 1 <= n <= 10⁴ 
// 
// Related Topics 广度优先搜索 数学 动态规划 👍 1399 👎 0


/**
 * 279, 完全平方数
 * @author Aaron Zhu
 * @date 2022-6-13
 */
public class PerfectSquares_279{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 数学解法
     */
    public static class Solution {
        public int numSquares(int n) {
            // 如果不满足 勒让德三平方和定理, 则其必然只能满足 拉格朗日四平方和定理
            // 即:  n = a*a + b*b + c*c + d*d
            if( checkAnswer4(n) ) {
                return 4;
            }

            // 判断是否为 n = a*a 场景
            if( isSquare(n) ) {
                return 1;
            }

            // 判断是否为 n = a*a + b*b 场景
            for (int a=1; a*a<=n; a++) {
                int b = n - a*a;
                if( isSquare(b) ) {
                    return 2;
                }
            }

            // 由于满足勒让德三平方和定理条件, 故此时只可能为3
            // 即:  n = a*a + b*b + c*c
            return 3;
        }

        /**
         * 判断n是否能表示为 4^k*(8m+7) 形式, 即不满足 勒让德三平方和定理
         * @param n
         * @return
         */
        private boolean checkAnswer4(int n) {
            while ( n%4==0 ) {
                n = n / 4;
            }

            boolean res = (n%8 == 7);
            return res;
        }

        /**
         * 判断是否为完全平方数
         * @param n
         * @return
         */
        private boolean isSquare(int n) {
            int x = (int)Math.sqrt(n);
            boolean res = ( x*x == n);
            return res;
        }
    }

    /**
     * DP
     */
    public static class Solution1 {
        public int numSquares(int n) {
            int[] dp = new int[n+1];
            for (int i=1; i<=n; i++) {
                int minJ = Integer.MAX_VALUE;
                for (int j=1; j*j<=i; j++) {
                    minJ = Math.min(minJ, dp[i-j*j]);
                }
                dp[i] = 1 + minJ;
            }
            return dp[n];
        }
    }
}
