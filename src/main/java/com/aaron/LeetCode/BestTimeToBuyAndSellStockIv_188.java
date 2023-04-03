package com.aaron.LeetCode;

import java.util.*;

//给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1： 
//
// 
//输入：k = 2, prices = [2,4,1]
//输出：2
//解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。 
//
// 示例 2： 
//
// 
//输入：k = 2, prices = [3,2,6,5,0,3]
//输出：7
//解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 
//。 
//
// 
//
// 提示： 
//
// 
// 0 <= k <= 100 
// 0 <= prices.length <= 1000 
// 0 <= prices[i] <= 1000 
// 
//
// Related Topics 数组 动态规划 👍 875 👎 0


/**
 * 188, 买卖股票的最佳时机 IV
 * @author Aaron Zhu
 * @date 2023-3-1
 */
public class BestTimeToBuyAndSellStockIv_188{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * DP
     */
    public static class Solution {
        public int maxProfit(int k, int[] prices) {
            // 第一维下标: 0: 前一天的数据; 1: 当天的数据
            // 第二维下标: 0: 买完第1笔股票；1: 卖完第1笔股票；2: 买完第2笔股票；3: 卖完第2笔股票；... 依次类推
            int[][] dp = new int[2][2*k];

            for(int i=0; i<2*k; i=i+2) {
                dp[0][i] = -prices[0];
                dp[0][i+1] = 0;
            }

            for(int i=1; i<prices.length; i++) {
                for(int j=0; j<2*k; j=j+2) {
                    if( j==0 ) {
                        dp[1][j] = Math.max(dp[0][0], -prices[i]);
                    } else {
                        dp[1][j] = Math.max(dp[0][j], dp[0][j-1]-prices[i]);
                    }
                    dp[1][j+1] = Math.max(dp[0][j+1], dp[0][j]+prices[i]);
                }

                for (int j=0; j<2*k; j++) {
                    dp[0][j] = dp[1][j];
                    //res = Math.max(res, dp[0][j]);
                }
            }

            return dp[0][2*k-1];
        }
    }

}