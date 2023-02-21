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
        int[] nums = new int[]{5,2,3,1};
        solution.sortArray(nums);
        System.out.println();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 堆排序：实现minHeap
 */
class Solution {

    private int[] minHeap;

    public int[] sortArray(int[] nums) {
        int size = nums.length;
        minHeap = new int[size+1];

        // 构建最小堆
        for(int i=1; i<=size; i++) {
            minHeap[i] = nums[i-1];
            swim(i);
        }



    }

    /**
     * 上浮i位置的元素
     * @param i
     */
    private void swim(int i) {
        // 父元素存在 且 当前元素 小于 父元素
        while (i>1 & minHeap[i] < minHeap[i/2] ) {
            int temp = minHeap[i];
            minHeap[i] = minHeap[i/2];
            minHeap[i/2] = temp;
            i = i/2;
        }
    }

}

//leetcode submit region end(Prohibit modification and deletion)

/**
 * 堆排序：优先队列
 */
class Solution3 {

    public int[] sortArray(int[] nums) {
        Queue<Integer> queue = new PriorityQueue<>( );
        for (int num : nums) {
            queue.add( num );
        }

        for (int i=0; i<nums.length; i++) {
            nums[i] = queue.remove();
        }

        return nums;
    }

}

/**
 * 归并排序
 */
class Solution2 {
    private static int[] aux;

    public int[] sortArray(int[] nums) {
        int size = nums.length;
        aux = new int[size];
        sort(nums, 0, size-1);
        return nums;
    }

    private void sort(int[] array, int left, int right) {
        if( left>=right ) {
            return;
        }

        int mid = left + (right-left)/2;
        sort(array, left, mid);
        sort(array, mid+1, right);
        merge(array, left, mid, right);
    }

    private void merge(int[] array, int left, int mid, int right) {
        for (int k=left; k<=right; k++) {
            aux[k] = array[k];
        }

        int i = left;   // 左半部分起始索引
        int j = mid+1;  // 右半部分起始索引

        for (int k=left; k<=right; k++) {

            if( i>mid ) {   // 左半分部分归并完成
                array[k] = aux[j];
                j++;
            } else if( j>right ) {  // 右半部分归并完成
                array[k] = aux[i];
                i++;
            } else if( aux[i]<aux[j] ) {
                array[k] = aux[i];
                i++;
            } else {
                array[k] = aux[j];
                j++;
            }
        }
    }

}


/**
 * 冒泡、选择、插入
 */
class Solution1 {
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
