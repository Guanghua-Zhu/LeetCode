package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可
//能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。 
//
// 
//
// 示例 1: 
//
// 输入: 12258
//输出: 5
//解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi" 
//
// 
//
// 提示： 
//
// 
// 0 <= num < 231 
// 
// Related Topics 字符串 动态规划 
// 👍 380 👎 0


/**
 * 剑指 Offer 46, 把数字翻译成字符串
 * @author Aaron Zhu
 * @date 2022-3-5
 */
public class Offer_46 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }


    /**
     * 动态规划
     */
    public static class Solution {
        public int translateNum(int num) {
            char[] chars = String.valueOf(num).toCharArray();
            int[] dp = new int[chars.length+1];
            dp[chars.length] = 1;
            for (int index = chars.length-1; index>=0; index--) {
                int ways = dp[index+1];
                if( index+1<chars.length ) {
                    if( chars[index]=='1' || (chars[index]=='2'&& chars[index+1]<='5') ) {
                        ways += dp[index+2];
                    }
                }
                dp[index] = ways;
            }
            return dp[0];
        }
    }

    /**
     * 暴力递归
     */
    public static class Solution1 {
        public int translateNum(int num) {
            char[] chars = String.valueOf(num).toCharArray();
            return calcWays(chars, 0);
        }

        private int calcWays(char[] chars, int index) {
            if( chars.length == index ) {
                return 1;
            }

            int ways = calcWays(chars, index+1);
            if( index+1<chars.length ) {
                if( chars[index]=='1' || (chars[index]=='2'&& chars[index+1]<='5') ) {
                    ways += calcWays(chars, index+2);
                }
            }
            return ways;
        }
    }

    /**
     * 暴力递归+记忆化
     */
    public static class Solution2 {
        public int translateNum(int num) {
            char[] chars = String.valueOf(num).toCharArray();
            int[] cache = new int[chars.length+1];
            return calcWays(chars, 0, cache);
        }

        private int calcWays(char[] chars, int index, int[] cache) {
            if( cache[index]!=0 ) {
                return cache[index];
            }

            if( index==chars.length ) {
                cache[index] = 1;
                return cache[index];
            }

            int ways = calcWays(chars, index+1, cache);
            if( index+1<chars.length ) {
                if( chars[index]=='1' || (chars[index]=='2'&& chars[index+1]<='5') ) {
                    ways += calcWays(chars, index+2, cache);
                }
            }
            cache[index] = ways;
            return cache[index];
        }
    }

}
