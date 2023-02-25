package com.aaron.LeetCode;

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
        Solution6 solution = new Solution6();
        int[] nums = new int[]{5,2,3,1};
        solution.sortArray(nums);
        System.out.println();
    }

    /**
     * 快速排序：三向切分
     */
    public static class Solution6 {
        private static final Random random = new Random();

        public int[] sortArray(int[] nums) {
            sortBy3Way(nums, 0, nums.length-1);
            return nums;
        }

        private void sortBy3Way(int[] nums, int left, int right) {
            if( left>=right ) {
                return;
            }

            int randomIndex = left + random.nextInt( right-left+1 );
            swap(nums, left, randomIndex);
            int partition = nums[left];

            int lt = left;
            int gt = right;
            int i = left+1;
            while (i<=gt) {
                int num = nums[i];
                if( num < partition ) {
                    swap(nums, i, lt);
                    lt++;
                    i++;
                } else if( num == partition ) {
                    i++;
                } else if( num > partition ) {
                    swap(nums, i, gt);
                    gt--;
                    // Note: 下面这行不可以写，原因在于gt元素属于未遍历元素
                    // 交换过来后，需要再下一轮循环中继续判断
                    // i++;
                }
            }

            sortBy3Way(nums, left, lt-1);
            sortBy3Way(nums, gt+1, right);
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * 快速排序：双指针
     */
    public static class Solution5 {

        private static final Random random = new Random();

        public int[] sortArray(int[] nums) {
            sort(nums, 0, nums.length-1);
            return nums;
        }

        private void sort(int[] nums, int left, int right) {
            if( left>=right ) {
                return;
            }

            int partitionIndex = partition(nums, left, right);
            sort(nums, left, partitionIndex-1);
            sort(nums, partitionIndex+1, right);
        }

        private int partition(int[] nums, int left, int right) {
            int randomIndex = left + random.nextInt( right-left+1 );
            swap(nums, left, randomIndex);

            int partition = nums[left];
            int i = left+1;
            int j = right;
            while (true) {
                while ( i<=right && nums[i]<partition ) {
                    i++;
                }
                while ( j>left && nums[j]>partition ) {
                    j--;
                }
                if( i>=j ) {
                    break;
                }
                swap(nums, i, j);
                i++;
                j--;
            }
            swap(nums, left, j);
            return j;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    /**
     * 堆排序：实现minHeap
     */
    public static class Solution4 {
        private int[] minHeap;

        public int[] sortArray(int[] nums) {
            int size = nums.length;
            minHeap = new int[size+1];

            // 方式1：通过上浮构建最小堆
            //        for(int i=1; i<=size; i++) {
            //            minHeap[i] = nums[i-1];
            //            swim(i);
            //        }

            // 方式2：通过下沉构建最小堆
            for (int i=0; i<size; i++) {
                minHeap[i+1] = nums[i];
            }
            for (int i=size/2; i>0; i--) {
                sink(i, size);
            }

            int N = size;
            for (int i=1; i<=size; i++) {
                nums[i-1] = minHeap[1];
                swap(1, N);
                N--;
                sink(1, N);
            }

            return nums;
        }

        /**
         * 上浮i位置的元素
         * @param i
         */
        private void swim(int i) {
            // 父元素存在 且 当前元素 小于 父元素
            while (i>1 & minHeap[i] < minHeap[i/2] ) {
                swap(i, i/2);
                i = i/2;
            }
        }

        /**
         * 在数量为N的堆中，下沉i位置的元素
         * @param i
         * @param N
         */
        private void sink(int i, int N) {
            while ( 2*i<=N) {
                int minIndex = i;
                if( minHeap[2*i] < minHeap[i] ) {
                    minIndex = 2*i;
                }
                if( 2*i+1<=N && minHeap[2*i+1] < minHeap[minIndex] ) {
                    minIndex = 2*i+1;
                }
                if( minIndex == i ) {
                    break;
                }
                swap(i, minIndex);
                i = minIndex;
            }
        }

        private void swap(int i, int j) {
            int temp = minHeap[i];
            minHeap[i] = minHeap[j];
            minHeap[j] = temp;
        }

    }

    /**
     * 堆排序：优先队列
     */
    public static class Solution3 {

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
    public static class Solution2 {
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
    public static class Solution1 {
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

}