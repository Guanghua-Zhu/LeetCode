package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 输入数字 n
// 按顺序打印出从 1 到最大的 n 位十进制数
// 比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999
//
// 示例 1: 
// 输入: n = 1
// 输出: [1,2,3,4,5,6,7,8,9]
//
// 说明：
// 用返回一个整数列表来代替打印
// n 为正整数 
// Related Topics 数组 数学
// 👍 179 👎 0


/**
 * 剑指 Offer 17, 打印从1到最大的n位数
 * @author Aaron Zhu
 * @date 2022-1-31
 */
public class Offer_17 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int[] printNumbers(int n) {
            if(n==0) {
                return new int[0];
            }

            int maxNum = calcMaxNum2(n);
            int[] result = new int[maxNum];
            for(int i=0; i<maxNum; i++) {
                result[i] = i+1;
            }
            return result;
        }

        private int calcMaxNum1(int n) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<n; i++) {
                sb.append("9");
            }
            String str = sb.toString();
            return Integer.valueOf(str);
        }

        private int calcMaxNum2(int n) {
            return (int)Math.pow(10,n) - 1;
        }
    }
}


