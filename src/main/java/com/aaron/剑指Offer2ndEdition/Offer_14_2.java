package com.aaron.剑指Offer2ndEdition;

import java.math.BigInteger;

//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1]
// 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘
//积是18。 
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 
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
// 
//
// 提示： 
//
// 
// 2 <= n <= 1000 
// 
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/ 
// Related Topics 数学 动态规划 
// 👍 167 👎 0

/**
 * 剑指 Offer 14- II, 剪绳子 II
 * @author Aaron Zhu
 * @date 2022-3-5
 */
public class Offer_14_2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 暴力递归+记忆化
     */
    public static class Solution {

        private final int MOD = 1000000007;

        public int cuttingRope(int n) {
            BigInteger[] cache = new BigInteger[n+1];
            BigInteger res = cut(n, cache);
            int ans = res.mod( BigInteger.valueOf(MOD) ).intValue();
            return ans;
        }

        private BigInteger cut(int length, BigInteger[] cache) {
            if( cache[length] != null ) {
                return cache[length];
            }

            if(length==2) {
                cache[length] = BigInteger.valueOf(1);
                return cache[length];
            }

            BigInteger res = BigInteger.valueOf(1);
            for(int subLength=2; subLength<length; subLength++) {
                BigInteger temp1 = BigInteger.valueOf( subLength*(length-subLength) );
                BigInteger temp2 = cut(length-subLength, cache).multiply( BigInteger.valueOf(subLength) );
                BigInteger temp3 = temp1.max(temp2);
                res = res.max(temp3);
            }

            cache[length] = res;
            return cache[length];
        }
    }

    /**
     * DP动态规划
     */
    public static class Solution2 {

        private final int MOD = 1000000007;

        public int cuttingRope(int n) {
            BigInteger[] dp = new BigInteger[n+1];
            dp[1] = BigInteger.valueOf(1);
            dp[2] = BigInteger.valueOf(1);
            for (int length=3; length<n+1; length++) {
                BigInteger res = BigInteger.valueOf(1);
                for(int subLength=2; subLength<length; subLength++) {
                    BigInteger temp1 = BigInteger.valueOf(subLength).multiply( BigInteger.valueOf(length-subLength) );
                    BigInteger temp2 = BigInteger.valueOf(subLength).multiply( dp[length-subLength] );
                    BigInteger temp3 = temp1.max( temp2 );
                    res = res.max( temp3 );
                }
                dp[length] = res;
            }

            int ans = dp[n].mod( BigInteger.valueOf(MOD) ).intValue();
            return ans;
        }

        private int cut(int length, int[] cache) {
            if( cache[length] != 0 ) {
                return cache[length];
            }

            if(length==2) {
                cache[length] = 1;
                return cache[length];
            }

            int res = -1;
            for(int subLength=2; subLength<length; subLength++) {
                int temp = Math.max( subLength*(length-subLength), subLength*cut(length-subLength, cache) );
                res = Math.max( res, temp );
            }
            res %= MOD;

            cache[length] = res;
            return cache[length];
        }
    }
}

