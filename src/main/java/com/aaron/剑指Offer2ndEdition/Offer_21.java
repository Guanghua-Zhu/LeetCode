package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 输入一个整数数组, 实现一个函数来调整该数组中数字的顺序
// 使得所有奇数在数组的前半部分，所有偶数在数组的后半部分
//
// 示例：
// 输入：nums = [1,2,3,4]
// 输出：[1,3,2,4]
// 注：[3,1,2,4] 也是正确的答案之一
//
// 提示： 
// 0 <= nums.length <= 50000
// 0 <= nums[i] <= 10000 
// Related Topics 数组 双指针 排序
// 👍 192 👎 0

/**
 * 剑指 Offer 21, 调整数组顺序使奇数位于偶数前面
 * @author Aaron Zhu
 * @date 2022-1-30
 */
public class Offer_21 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 首尾双指针
     */
    public static class Solution {
        public int[] exchange(int[] nums) {
            if( nums==null || nums.length==0 ) {
                return nums;
            }

            // 偶数指针
            int evenPoint = 0;
            // 奇数指针
            int oddPoint = nums.length-1;

            while ( evenPoint<oddPoint ) {
                // 未找到偶数, 继续向右移动
                if(nums[evenPoint]%2 == 1) {
                    evenPoint++;
                    continue;
                }

                // 未找到奇数, 继续向左移动
                if(nums[oddPoint]%2 == 0) {
                    oddPoint--;
                    continue;
                }

                int temp = nums[evenPoint];
                nums[evenPoint] = nums[oddPoint];
                nums[oddPoint] = temp;
            }
            return nums;
        }
    }

    /**
     * 首尾双指针
     */
    public static class Solution1 {
        public int[] exchange(int[] nums) {
            if( nums==null || nums.length==0 ) {
                return nums;
            }

            // 偶数指针
            int evenPoint = 0;
            // 奇数指针
            int oddPoint = nums.length-1;

            while ( evenPoint<oddPoint ) {
                while ( evenPoint < oddPoint ) {
                    if( nums[evenPoint]%2==0 ) {
                        // 找到偶数
                        break;
                    }
                    evenPoint++;
                }
                while ( evenPoint < oddPoint ) {
                    if( nums[oddPoint]%2==1 ) {
                        // 找到奇数
                        break;
                    }
                    oddPoint--;
                }

                int temp = nums[evenPoint];
                nums[evenPoint] = nums[oddPoint];
                nums[oddPoint] = temp;
            }
            return nums;
        }
    }

    /**
     * 快慢双指针
     */
    public static class Solution2 {
        public int[] exchange(int[] nums) {
            if(nums==null ||  nums.length==0) {
                return nums;
            }

            // 快指针: 搜索奇数
            int fast = 0;
            // 慢指针: 奇数应该存放的位置
            int slow = 0;

            while (fast < nums.length) {
                // 发现奇数
                if(nums[fast]%2 == 1) {
                    int temp = nums[slow];
                    nums[slow] = nums[fast];
                    nums[fast] = temp;
                    // 更新下一个奇数存放的位置索引
                    slow++;
                }
                fast++;
            }
            return nums;
        }
    }

}


