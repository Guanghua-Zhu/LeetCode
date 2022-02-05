package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次
// 请写程序找出这两个只出现一次的数字
// 要求时间复杂度是O(n)，空间复杂度是O(1)
//
// 示例 1：
// 输入：nums = [4,1,4,6]
// 输出：[1,6] 或 [6,1]
//
// 示例 2： 
// 输入：nums = [1,2,10,4,1,4,3,3]
// 输出：[2,10] 或 [10,2]
//
// 限制：
// 2 <= nums.length <= 10000
// Related Topics 位运算 数组
// 👍 532 👎 0


/**
 * 剑指 Offer 56 - I, 数组中数字出现的次数
 * @author Aaron Zhu
 * @date 2022-2-5
 */
public class Offer_56_1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {

        public int[] singleNumbers(int[] nums) {
            int result = 0;
            for(int num : nums) {
                result ^= num;
            }

            int bit = 1;
            while ( (result&bit) == 0 ) {
                bit = bit << 1;
            }

            int num1 = 0;
            int num2 = 0;
            for (int num : nums) {
                if( (num&bit) == 0 ) {
                    num1 ^= num;
                } else {
                    num2 ^= num;
                }
            }

            return new int[]{num1, num2};
        }
    }

    public static class Solution1 {
        public int[] singleNumbers(int[] nums) {
            Map<Integer, Integer> count = new HashMap<>();
            for (int num : nums) {
                count.compute(num, (k,v)->{
                    if(v==null) {
                        v = 0;
                    }
                    v++;
                    return v;
                });
            }

            int[] res = new int[2];
            int i = 0;
            for (Map.Entry<Integer, Integer> entry: count.entrySet() ) {
                if( entry.getValue() == 1 ) {
                    res[i] = entry.getKey();
                    i++;
                }
            }
            return res;
        }
    }
}

