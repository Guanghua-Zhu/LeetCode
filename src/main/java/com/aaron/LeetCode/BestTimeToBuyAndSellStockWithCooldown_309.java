package com.aaron.LeetCode;

import java.util.*;

//给定一个整数数组
// prices，其中第 prices[i] 表示第 i 天的股票价格 。 
//
// 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）: 
//
// 
// 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。 
// 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 
//输入: prices = [1,2,3,0,2]
//输出: 3 
//解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出] 
//
// 示例 2: 
//
// 
//输入: prices = [1]
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 5000 
// 0 <= prices[i] <= 1000 
// 
//
// Related Topics 数组 动态规划 👍 1420 👎 0


/**
 * 309, 最佳买卖股票时机含冷冻期
 * @author Aaron Zhu
 * @date 2023-3-1
 */
public class BestTimeToBuyAndSellStockWithCooldown_309{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = new int[]{1,2,3,0,2};
        solution.maxProfit(prices);
        System.out.println("gg");
    }

    /**
     * DP
     */
    public static class Solution {
        public int maxProfit(int[] prices) {
            int[][] dp = new int[2][4];
            // 第1维下标: 0: 上一天, 1: 当天
            // 第2维下标: 0: 持有股票, 1: 刚卖完股票, 2: 处于冷冻期,   3: 解冻期,可买入股票
            dp[0][0] = -prices[0];
            dp[0][1] = 0;
            dp[0][2] = 0;
            dp[0][3] = 0;

            for (int i=1; i<prices.length; i++) {
                dp[1][0] = maxBy3(dp[0][0], dp[0][2]-prices[i], dp[0][3]-prices[i]);
                dp[1][1] = dp[0][0]+prices[i];
                dp[1][2] = dp[0][1];
                dp[1][3] = Math.max(dp[0][2], dp[0][3]);

                dp[0][0] = dp[1][0];
                dp[0][1] = dp[1][1];
                dp[0][2] = dp[1][2];
                dp[0][3] = dp[1][3];
            }

            return maxBy3(dp[0][1], dp[0][2], dp[0][3]);
        }

        private static int maxBy3(int a, int b, int c) {
            int temp = Math.max(a,b);
            return Math.max(temp, c);
        }
    }

}
