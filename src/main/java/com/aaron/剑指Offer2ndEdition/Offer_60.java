package com.aaron.剑指Offer2ndEdition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
//
// 
//
// 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。 
//
// 
//
// 示例 1: 
//
// 输入: 1
//输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
// 
//
// 示例 2: 
//
// 输入: 2
//输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0
//.05556,0.02778] 
//
// 
//
// 限制： 
//
// 1 <= n <= 11 
// Related Topics 数学 动态规划 概率与统计 
// 👍 370 👎 0


/**
 * 剑指 Offer 60, n个骰子的点数
 * @author Aaron Zhu
 * @date 2022-3-5
 */
public class Offer_60 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        double[] res = solution.dicesProbability(2);
        System.out.println("gg");
    }

    /**
     * DP动态规划
     */
    public static class Solution {
        public double[] dicesProbability(int n) {
            int[][] dp = new int[n+1][6*n+1];
            for(int i=1; i<=6; i++) {
                dp[1][i] = 1;
            }

            for(int num=2; num<=n; num++) {
                for(int sum=1*num; sum<=6*num; sum++) {
                    int count = 0;
                    for(int i=1; i<=6; i++) {
                        // 第num-1次掷骰子后的点数和
                        int lastSum = sum-i;
                        if( lastSum>0 ) {
                            count += dp[num-1][lastSum];
                        }
                    }
                    dp[num][sum] = count;
                }
            }

            List<Double> list = new ArrayList<>();
            double allCount = Math.pow(6,n);
            for (int sum=n; sum<=6*n; sum++) {
                int count = dp[n][sum];
                if( count!=0 ) {
                    double p = count / allCount;
                    list.add( p );
                }
            }

            double[] res = new double[list.size()];
            for(int i=0; i<res.length; i++) {
                res[i] = list.get(i);
            }
            return res;
        }
    }

    /**
     * 暴力递归+记忆化
     */
    public static  class Solution1 {
        public double[] dicesProbability(int n) {
            int[][] cache = new int[n+1][6*n+1];
            for (int i=0; i<n+1; i++) {
                Arrays.fill(cache[i], -1);
            }

            List<Double> list = new ArrayList<>();
            double allCount = Math.pow(6,n);

            for (int sum=n; sum<=6*n; sum++) {
                int count = calcCount(n, sum, cache);
                double p = count / allCount;
                list.add( p );
            }

            double[] res = new double[list.size()];
            for(int i=0; i<res.length; i++) {
                res[i] = list.get(i);
            }
            return res;
        }

        /**
         * 计算第num次掷骰子, 和为num的次数
         * @param num
         * @param sum
         * @return
         */
        private int calcCount(int num, int sum, int[][] cache) {
            if( cache[num][sum]!=-1 ) {
                return cache[num][sum];
            }

            if( num==1 ) {
                if( sum>=1 && sum<=6 ) {
                    cache[1][sum] = 1;
                } else {
                    cache[1][sum] = 0;
                }
                return cache[1][sum];
            }

            int count = 0;
            for(int i=1; i<=6; i++) {
                // 第num-1次掷骰子后的点数和
                int lastSum = sum-i;
                if( lastSum>0 ) {
                    count += calcCount(num-1, lastSum, cache);
                }
            }

            cache[num][sum] = count;
            return cache[num][sum];
        }

    }

}