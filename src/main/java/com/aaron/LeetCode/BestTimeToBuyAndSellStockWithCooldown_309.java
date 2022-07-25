package com.aaron.LeetCode;

import java.util.*;

//给定一个整数数组 prices，其中第 prices[i] 表示第 i 天的股票价格 。 
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
// Related Topics 数组 动态规划 👍 1265 👎 0


/**
 * 309, 最佳买卖股票时机含冷冻期
 * @author Aaron Zhu
 * @date 2022-7-20
 */
public class BestTimeToBuyAndSellStockWithCooldown_309{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int maxProfit(int[] prices) {
            int f0 = 0-prices[0];   // 持股
            int f1 = 0;             // 不持股 且 在冷冻期
            int f2 = 0;             // 不持股 且 不在冷冻期

            for (int i=1; i<prices.length; i++) {
                int newF0 = Math.max( f0, f2-prices[i] );
                int newF1 = f0 + prices[i];
                int newF2 = Math.max( f1, f2 );

                f0 = newF0;
                f1 = newF1;
                f2 = newF2;
            }

            return Math.max(f1,f2);
        }
    }

}

