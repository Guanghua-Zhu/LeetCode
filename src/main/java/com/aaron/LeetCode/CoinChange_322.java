package com.aaron.LeetCode;

import java.util.*;

//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。 
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 2³¹ - 1 
// 0 <= amount <= 10⁴ 
// 
// Related Topics 广度优先搜索 数组 动态规划 👍 2059 👎 0


/**
 * 322, 零钱兑换
 * @author Aaron Zhu
 * @date 2022-7-28
 */
public class CoinChange_322{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] coins = new int[]{1,2,5};
        int amount = 100;
        solution.coinChange(coins, amount);
        System.out.println("gg");
    }

    /**
     * DP
     */
    public static class Solution {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount+1];

            for (int j=1; j<=amount; j++) {
                int min = Integer.MAX_VALUE;
                for (int k=0; k<coins.length; k++) {
                    int amount2 = j - coins[k];
                    if( amount2 >= 0 && dp[amount2]!=-1) {
                        min = Math.min( min, dp[amount2]+1 );
                    }
                }

                if( min==Integer.MAX_VALUE ) {
                    dp[j] = -1;
                } else {
                    dp[j] = min;
                }
            }


            return dp[ amount];
        }
    }

    /**
     * 记忆化搜索
     */
    public static class Solution1 {
        private int[] cache;

        public int coinChange(int[] coins, int amount) {
            cache = new int[ amount+1 ];
            Arrays.fill(cache, -2);
            Arrays.sort( coins );
            return findWay(coins, amount);
        }

        private int findWay(int[] coins, int amount) {
            if( amount==0 ) {
                return 0;
            } else if( amount<0 ) {
                return -1;
            }

            if( cache[amount] !=-2 ) {
                return cache[amount];
            }

            int min = Integer.MAX_VALUE;
            for (int i=coins.length-1; i>=0; i--) {
                int res = findWay(coins, amount-coins[i]);
                if( res>=0 && res<min ) {
                    min = res+1;
                }
            }

            if( min==Integer.MAX_VALUE ) {
                cache[amount] = -1;
            } else {
                cache[amount] = min;
            }

            return cache[amount];
        }

    }

}

