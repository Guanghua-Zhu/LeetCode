package com.aaron.LeetCode;

import java.util.*;

//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：2
//解释：有两种方法可以爬到楼顶。
//1. 1 阶 + 1 阶
//2. 2 阶 
//
// 示例 2： 
//
// 
//输入：n = 3
//输出：3
//解释：有三种方法可以爬到楼顶。
//1. 1 阶 + 1 阶 + 1 阶
//2. 1 阶 + 2 阶
//3. 2 阶 + 1 阶
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 45 
// 
// Related Topics 记忆化搜索 数学 动态规划 
// 👍 2386 👎 0


/**
 * 70, 爬楼梯
 * @author Aaron Zhu
 * @date 2022-4-25
 */
public class ClimbingStairs_70{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * DP动态规划
     */
    public static class Solution {
        // f(n) = f(n-1) + f(n-2)
        public int climbStairs(int n) {
            if( n<=2 ) {
                return n;
            }

            int lastBy1 = 2;
            int lastBy2 = 1;
            int res = 0;
            int count = n - 2;
            while ( count>0 ) {
                res = lastBy1 + lastBy2;
                lastBy2 = lastBy1;
                lastBy1 = res;
                count--;
            }
            return res;
        }
    }

}
