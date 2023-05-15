package com.aaron.LeetCode;

import java.util.*;

//有 n 根长度互不相同的木棍，长度为从 1 到 n 的整数。请你将这些木棍排成一排，并满足从左侧 可以看到 恰好 k 根木棍。从左侧 可以看到 木棍的前提是
//这个木棍的 左侧 不存在比它 更长的 木棍。 
//
// 
// 例如，如果木棍排列为 [1,3,2,5,4] ，那么从左侧可以看到的就是长度分别为 1、3 、5 的木棍。 
// 
//
// 给你 n 和 k ，返回符合题目要求的排列 数目 。由于答案可能很大，请返回对 10⁹ + 7 取余 的结果。 
//
// 
//
// 示例 1： 
//
// 输入：n = 3, k = 2
//输出：3
//解释：[1,3,2], [2,3,1] 和 [2,1,3] 是仅有的能满足恰好 2 根木棍可以看到的排列。
//可以看到的木棍已经用粗体+斜体标识。
// 
//
// 示例 2： 
//
// 输入：n = 5, k = 5
//输出：1
//解释：[1,2,3,4,5] 是唯一一种能满足全部 5 根木棍可以看到的排列。
//可以看到的木棍已经用粗体+斜体标识。
// 
//
// 示例 3： 
//
// 输入：n = 20, k = 11
//输出：647427950
//解释：总共有 647427950 (mod 109 + 7) 种能满足恰好有 11 根木棍可以看到的排列。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 1000 
// 1 <= k <= n 
// 
//
// Related Topics 数学 动态规划 组合数学 👍 65 👎 0


/**
 * 1866, 恰有 K 根木棍可以看到的排列数目
 * @author Aaron Zhu
 * @date 2023-5-11
 */
public class NumberOfWaysToRearrangeSticksWithKSticksVisible_1866{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 组合数学：第一类斯特林数
     */
    public static class Solution {
        private static long mod = 1_000_000_007;

        public int rearrangeSticks(int n, int k) {
            if( n==k ) {
                return 1;
            } else if( n<k ) {
                return 0;
            } else if( n>0 && k==0 ) {
                return 0;
            }

            long[][] dp = new long[2][k+1];
            dp[0][0] = 1;
            int newIndex = 0;
            for (int i=1; i<=n; i++) {
                newIndex = 1 - newIndex;
                int oldIndex = 1 - newIndex;

                dp[newIndex][0] = 0;    // s(n,0) = 0
                // 当k>n时有s(n,k)=0, 故增加j<=i条件进行剪枝
                for (int j=1; j<=k && j<=i ; j++) {
                    dp[newIndex][j] = ( dp[oldIndex][j-1] + (i-1)*dp[oldIndex][j] ) % mod;
                }
            }

            return (int)dp[newIndex][k];
        }
    }

}