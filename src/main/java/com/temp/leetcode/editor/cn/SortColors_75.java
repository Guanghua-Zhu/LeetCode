package com.temp.leetcode.editor.cn;

import java.util.*;

//给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。 
//
// 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 
//
// 
// 
//
// 必须在不使用库的sort函数的情况下解决这个问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,0,2,1,1,0]
//输出：[0,0,1,1,2,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,0,1]
//输出：[0,1,2]
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// nums[i] 为 0、1 或 2 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以不使用代码库中的排序函数来解决这道题吗？ 
// 你能想出一个仅使用常数空间的一趟扫描算法吗？ 
// 
// Related Topics 数组 双指针 排序 
// 👍 1250 👎 0


/**
 * 75, 颜色分类
 * @author Aaron Zhu
 * @date 2022-4-28
 */
public class SortColors_75{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 双指针-方式1
 */
class Solution {
    public void sortColors(int[] nums) {
        int p0 = 0;
        int p1 = 0;
        for(int i=0; i<nums.length; i++) {
            if( nums[i]==1) {
                swap(nums, i, p1);
                p1++;
            } else if( nums[i]==0 ) {
                swap(nums, i, p0);
                if( p0<p1 ) {
                    swap(nums, i, p1);
                }
                p0++;
                p1++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution3 {
    public void sortColors(int[] nums) {
        int p = 0;
        // 先把0交换到指针p所指示的位置处
        for(int i=0; i<nums.length; i++) {
            if( nums[i]==0 ) {
                int temp = nums[i];
                nums[i] = nums[p];
                nums[p] = temp;
                p++;
            }
        }

        // 再把1交换到指针p所指示的位置处
        for(int i=p; i<nums.length; i++) {
            if( nums[i]==1 ) {
                int temp = nums[i];
                nums[i] = nums[p];
                nums[p] = temp;
                p++;
            }
        }
    }
}

/**
 * 计数排序
 */
class Solution2 {
    public void sortColors(int[] nums) {
        int num0 = 0;
        int num1 = 0;
        int num2 = 0;
        for (int num : nums) {
            if( num==0 ) {
                num0++;
            } else if (num == 1) {
                num1++;
            } else if (num == 2) {
                num2++;
            }
        }

        for (int i=0; i< nums.length; i++) {
            if( num0>0 ) {
                nums[i] = 0;
                num0--;
            } else if( num1>0 ) {
                nums[i] = 1;
                num1--;
            } else if( num2>0 ) {
                nums[i] = 2;
                num2--;
            }
        }
    }
}

/**
 * 冒泡排序
 */
class Solution1 {
    public void sortColors(int[] nums) {
        for (int i=0; i<nums.length-1; i++) {
            for (int j=0; j<nums.length-1-i; j++) {
                if( nums[j]>nums[j+1] ) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
    }
}
