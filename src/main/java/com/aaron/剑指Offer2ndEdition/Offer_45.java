package com.aaron.剑指Offer2ndEdition;

import java.util.*;
import java.util.stream.Collectors;

//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。 
//
// 
//
// 示例 1: 
//
// 输入: [10,2]
//输出: "102" 
//
// 示例 2: 
//
// 输入: [3,30,34,5,9]
//输出: "3033459" 
//
// 
//
// 提示: 
//
// 
// 0 < nums.length <= 100 
// 
//
// 说明: 
//
// 
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数 
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0 
// 
// Related Topics 贪心 字符串 排序 
// 👍 380 👎 0


/**
 * 剑指 Offer 45, 把数组排成最小的数
 * @author Aaron Zhu
 * @date 2022-2-16
 */
public class Offer_45 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String res = solution.minNumber( new int[]{8308,308} );
        System.out.println("gg");
    }

    public static class Solution {
        public String minNumber(int[] nums) {
            return Arrays.stream(nums)
                .boxed()
                .sorted( (num1, num2)->compare(num1,num2) )
                .map( Object::toString )
                .collect(Collectors.joining());
        }

        private int compare(Integer num1, Integer num2) {
            String res1 = num1+""+num2;
            String res2 = num2+""+num1;
            long res = Long.valueOf(res1) - Long.valueOf(res2);
            return (int)res;
        }

    }
}
