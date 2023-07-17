package com.aaron.LeetCode;

import java.util.*;

//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。 
//
// 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,2]
//输出："210" 
//
// 示例 2： 
//
// 
//输入：nums = [3,30,34,5,9]
//输出："9534330"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 10⁹ 
// 
//
// Related Topics 贪心 数组 字符串 排序 👍 1156 👎 0


/**
 * 179, 最大数
 * @author Aaron Zhu
 * @date 2023-7-17
 */
public class LargestNumber_179{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1000000000, 1000000000};
        System.out.println("gg");
    }

    public static class Solution {
        public String largestNumber(int[] nums) {
            int size = nums.length;
            if( size==0 ) {
                return "";
            } else if( size==1 ) {
                return String.valueOf(nums[0]);
            }

            String[] array = new String[size];
            for (int i=0; i<size; i++) {
                array[i] = String.valueOf(nums[i]);
            }
            Arrays.sort(array, (a1,a2)->{
                String str1 = a1+a2;
                String str2 = a2+a1;
                // 字典序降序
                return -1 * str1.compareTo(str2);
            });

            StringBuilder sb = new StringBuilder();
            for (String str : array) {
                sb.append(str);
            }

            // 移除前导零
            int start = 0;
            while( start<=sb.length()-2 && sb.charAt(start)=='0' ) {
                start++;
            }

            return sb.substring(start);
        }
    }
}