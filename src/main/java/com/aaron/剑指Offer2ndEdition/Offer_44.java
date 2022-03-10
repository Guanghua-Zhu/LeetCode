package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，
//等等。 
//
// 请写一个函数，求任意第n位对应的数字。 
//
// 
//
// 示例 1： 
//
// 输入：n = 3
//输出：3
// 
//
// 示例 2： 
//
// 输入：n = 11
//输出：0 
//
// 
//
// 限制： 
//
// 
// 0 <= n < 2^31 
// 
//
// 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/ 
// Related Topics 数学 二分查找 
// 👍 209 👎 0


/**
 * 剑指 Offer 44, 数字序列中某一位的数字
 * @author Aaron Zhu
 * @date 2022-3-10
 */
public class Offer_44 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findNthDigit(11);
        System.out.println("gg");
    }

    public static class Solution {
        /**
         * Key: 该索引范围是几位数; Value: 索引范围最大值
         */
        private static Map<Integer, Long> map;

        static {
            map = new LinkedHashMap<>();
            map.put(1, (long)9);
            long lastMaxIndex=9;
            for(int i=2;;i++) {
                long maxIndex = lastMaxIndex + (long) Math.pow(10, i-1)*9*i;
                map.put(i, maxIndex);
                lastMaxIndex = maxIndex;
                if( maxIndex>Integer.MAX_VALUE ) {
                    break;
                }
            }
        }

        public int findNthDigit(int n) {
            int digit = calcDigit(n);
            long startIndex = 0;
            if(digit>1) {
                startIndex =  map.get(digit-1)+1;
            }

            long num = (long) Math.pow(10,digit-1) + (n-startIndex)/digit;
            if( digit==1 ) {
                num = num - 1;
            }

            String str = String.valueOf(num);
            int strIndex = (int)(n-startIndex) % digit;
            char ch = str.charAt( strIndex );
            int res = Character.digit(ch, 10);
            return res;
        }

        /**
         * 根据索引位置计算位数
         *  1位数: 0 ~ 9
         *  2位数: 10 ~ 189
         *  3位数: 190 ~ 2889
         */
        private int calcDigit(int index) {
            int res = -1;
            for (Map.Entry<Integer, Long> entry : map.entrySet()) {
                int digit = entry.getKey();
                long maxIndex = entry.getValue();
                if(index <= maxIndex) {
                    res = digit;
                    break;
                }
            }
            return res;
        }
    }
}


