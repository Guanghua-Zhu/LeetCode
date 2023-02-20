package com.temp.leetcode.editor.cn;

import java.util.*;

//给你一个整数数组 nums，请你将该数组升序排列。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,2,3,1]
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：nums = [5,1,1,2,0,0]
//输出：[0,0,1,1,2,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// -5 * 10⁴ <= nums[i] <= 5 * 10⁴ 
// 
//
// Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序 👍 782 👎 0


/**
 * 912, 排序数组
 * @author Aaron Zhu
 * @date 2023-2-20
 */
public class SortAnArray_912{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)


class Solution {

    // 插入排序
    public int[] sortArray(int[] nums) {
        int size = nums.length;
        for (int i=1; i<size; i++) {
            int temp = nums[i];
            int j = i-1;
            for( ; j>=0 && nums[j]>temp; j--) {
                nums[j+1] = nums[j];
            }
            nums[j+1] = temp;
        }

        return nums;
    }

    // 选择排序
    public int[] sortArray2(int[] nums) {
        int  size = nums.length;
        for (int i=0; i<size-1; i++) {
            int minIndex = i;

            for (int j=i+1; j<size; j++) {
                if( nums[j] < nums[minIndex] ) {
                    minIndex = j;
                }
            }

            if( minIndex!=i ) {
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
        }

        return nums;
    }

    // 冒泡排序
    public int[] sortArray1(int[] nums) {
        int  size = nums.length;
        for (int i=0; i<size-1; i++) {
            for (int j=0; j<size-1-i; j++) {
                if( nums[j]>nums[j+1] ) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }

        return nums;
    }

}

//leetcode submit region end(Prohibit modification and deletion)
