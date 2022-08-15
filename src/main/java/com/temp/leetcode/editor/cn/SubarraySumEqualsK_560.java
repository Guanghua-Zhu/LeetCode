package com.temp.leetcode.editor.cn;

import java.util.*;

//给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1], k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3], k = 3
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -1000 <= nums[i] <= 1000 
// -10⁷ <= k <= 10⁷ 
// 
// Related Topics 数组 哈希表 前缀和 👍 1621 👎 0


/**
 * 560, 和为 K 的子数组
 * @author Aaron Zhu
 * @date 2022-8-12
 */
public class SubarraySumEqualsK_560{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        int size = nums.length;
        int sum = 0;
        Map<Integer, Integer> preSumByCount = new HashMap<>();
        preSumByCount.put( 0,1 );
        for (int i=0; i<size; i++) {
            sum += nums[i];
            res += preSumByCount.getOrDefault(sum-k, 0);
            preSumByCount.put(sum, )
        }


        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

class Solution2 {
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        int size = nums.length;
        int[] sum = new int[size];
        for (int i=0; i<size; i++) {
            if( i==0 ) {
                sum[i] = nums[i];
            } else {
                sum[i] = sum[i-1] + nums[i];
            }

            if( sum[i] == k ) {
                res++;
            }
        }

        for (int i=1; i<size; i++) {
            for (int j=i; j<size; j++) {
                int tempSum = sum[j] - sum[i-1];
                if( tempSum==k ) {
                    res++;
                }
            }
        }

        return res;

    }

}
