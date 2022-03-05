package com.aaron.剑指Offer2ndEdition;

//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
//请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
//。 
//
// 示例 1： 
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1 
//
// 示例 2: 
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36 
//
// 提示： 
//
// 
// 2 <= n <= 58 
// 
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/ 
// Related Topics 数学 动态规划 
// 👍 375 👎 0


/**
 * 剑指 Offer 14- I, 剪绳子
 * @author Aaron Zhu
 * @date 2022-3-5
 */
public class Offer_14_1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * DP动态规划
     */
    public static class Solution {
        public int cuttingRope(int n) {
            int[] dp = new int[n+1];
            dp[2] = 1;
            for(int length=3; length<n+1; length++) {
                int res = -1;
                for (int i=2; i<length; i++) {
                    int temp = Math.max( i*(length-i), i*dp[length-i] );
                    res = Math.max( res, temp );
                }
                dp[length] = res;
            }
            return dp[n];
        }
    }

    /**
     * 暴力递归+记忆化
     */
    public static class Solution1 {
        public int cuttingRope(int n) {
            int[] cache = new int[n+1];
            return cutting(n, cache);
        }

        private int cutting(int length, int[] cache) {
            if( cache[length]!=0 ) {
                return cache[length];
            }

            if( length==2 ) {
                cache[length] = 1;
                return 1;
            }

            int res = 1;
            for (int i=2; i<length; i++) {
                int temp = Math.max( i*(length-i), i*cutting(length-i, cache) );
                res = Math.max(res, temp);
            }

            cache[length] = res;
            return res;
        }




    }

}
