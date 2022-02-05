package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次
// 请找出那个只出现一次的数字
//
// 示例 1： 
// 输入：nums = [3,4,3,3]
// 输出：4
//
// 示例 2：
// 输入：nums = [9,1,7,9,7,9,7]
// 输出：1
//
// 限制：
// 1 <= nums.length <= 10000
// 1 <= nums[i] < 2^31 
// Related Topics 位运算 数组
// 👍 270 👎 0


/**
 * 剑指 Offer 56-II, 数组中数字出现的次数II
 * @author Aaron Zhu
 * @date 2022-2-5
 */
public class Offer_56_2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = new int[]{3,4,3,3};
        solution.singleNumber(a);
        System.out.println("gg");
    }


    /**
     * 位运算
     */
    public static class Solution {
        public int singleNumber(int[] nums) {
            // int类型在二进制下的位数
            int intBitNum = 32;
            int[] counts = new int[intBitNum];

            // 统计各元素在各二进制位为1的次数
            for(int i=0; i<nums.length; i++) {
                for(int j=0; j<intBitNum; j++) {
                    // 获取元素的最后一位
                    int bit = nums[i] & 1;
                    counts[j] += bit;
                    nums[i] >>>= 1;
                }
            }

            int res = 0;
            for(int i=intBitNum-1; i>=0; i--) {
                counts[i] = counts[i] % 3;
                res = res<<1 | counts[i];
            }
            return res;
        }
    }

    /**
     * 遍历
     */
    public static class Solution1 {
        public int singleNumber(int[] nums) {
            Map<Integer, Integer> count = new HashMap<>();
            for (int num : nums) {
                count.compute(num, (k,v)->{
                    if(v==null) {
                        return 1;
                    }
                    v++;
                    return v;
                });
            }

            int result = 0;
            for (Map.Entry<Integer, Integer> entry: count.entrySet()) {
                if( entry.getValue()==1 ) {
                    result = entry.getKey();
                    break;
                }
            }
            return result;
        }
    }

}
