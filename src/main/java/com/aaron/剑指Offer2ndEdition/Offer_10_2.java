package com.aaron.剑指Offer2ndEdition;

//一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。 
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 示例 1： 
//
// 输入：n = 2
//输出：2
// 
//
// 示例 2： 
//
// 输入：n = 7
//输出：21
// 
//
// 示例 3： 
//
// 输入：n = 0
//输出：1 
//
// 提示： 
//
// 
// 0 <= n <= 100 
// 
//
// 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/ 
//
// 
// Related Topics 记忆化搜索 数学 动态规划 
// 👍 234 👎 0


/**
 * 剑指offer·第二版: 10-II, 青蛙跳台阶问题
 * @author Aaron Zhu
 * @date 2022-1-9
 */
public class Offer_10_2{
    public static void main(String[] args) {
       Solution solution = new Solution();
    }

    public static class Solution {
        public static int mod = 1000000007;

        public int numWays(int n) {
            // F(0)=1, F(1)=1
            int num1 = 1;
            int num2 = 1;
            int result = 0;
            // F(n+2) = F(n) + F(n+1)
            // result = num1 + num2
            for(int i=0; i<n; i++) {
                result = (num1 + num2)%mod;
                num1 = num2;
                num2 = result;
            }
            return num1;
        }
    }
}

