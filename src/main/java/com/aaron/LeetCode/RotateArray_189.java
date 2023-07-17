package com.aaron.LeetCode;


//给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,2,3,4,5,6,7], k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右轮转 1 步: [7,1,2,3,4,5,6]
//向右轮转 2 步: [6,7,1,2,3,4,5]
//向右轮转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 
//输入：nums = [-1,-100,3,99], k = 2
//输出：[3,99,-1,-100]
//解释: 
//向右轮转 1 步: [99,-1,-100,3]
//向右轮转 2 步: [3,99,-1,-100] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 0 <= k <= 10⁵ 
// 
//
// 
//
// 进阶： 
//
// 
// 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。 
// 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？ 
// 
//
// Related Topics 数组 数学 双指针 👍 1877 👎 0


/**
 * 189, 轮转数组
 * @author Aaron Zhu
 * @date 2023-7-17
 */
public class RotateArray_189{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     *  数组翻转
     */
    public static class Solution {

        public void rotate(int[] nums, int k) {
            int N = nums.length;
            if( N<=1 ) {
                return;
            }
            k = k % N;
            if( k==0 ) {
                return;
            }

            reverse(nums, 0, N-1);
            reverse(nums, 0, k-1);
            reverse(nums, k, N-1);
        }

        public void reverse(int[] array, int i, int j) {
            while (i<j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

    }

    /**
     *  利用交换元素的数量作为结束条件
     */
    public static class Solution4 {

        public void rotate(int[] nums, int k) {
            int N = nums.length;
            if( N<=1 ) {
                return;
            }
            k = k % N;
            if( k==0 ) {
                return;
            }

            // 统计交换的元素
            int count = 0;
            for (int start=0; count<N; start++) {
                int cur = start;
                int nextNum = nums[start];
                while ( true ) {
                    int nextIndex = (cur+k) % N;

                    // 交换 nextNum、nums[nextIndex], 实现将nextNum放置到旋转后的nextIndex位置上
                    int temp = nums[nextIndex];
                    nums[nextIndex] = nextNum;
                    nextNum = temp;

                    // 统计交换的元素
                    count++;

                    cur = nextIndex;
                    if( cur==start ) {
                        break;
                    }
                }
            }

            return;
        }

        /**
         * gcd(a,b) = gcd(b, a%b)
         * @param a
         * @param b
         * @return
         */
        public int gcd(int a, int b) {
            if( b==0 ) {
                return a;
            }
            return gcd(b, a%b);
        }
    }

    /**
     *  利用gcd作为结束条件
     */
    public static class Solution3 {

        public void rotate(int[] nums, int k) {
            int N = nums.length;
            if( N<=1 ) {
                return;
            }
            k = k % N;
            if( k==0 ) {
                return;
            }

            int count = gcd(N,k);
            for (int start=0; start<count; start++) {
                int cur = start;
                int nextNum = nums[start];
                while ( true ) {
                    int nextIndex = (cur+k) % N;

                    // 交换 nextNum、nums[nextIndex], 实现将nextNum放置到旋转后的nextIndex位置上
                    int temp = nums[nextIndex];
                    nums[nextIndex] = nextNum;
                    nextNum = temp;

                    cur = nextIndex;
                    if( cur==start ) {
                        break;
                    }
                }
            }
        }

        /**
         * gcd(a,b) = gcd(b, a%b)
         * @param a
         * @param b
         * @return
         */
        public int gcd(int a, int b) {
            if( b==0 ) {
                return a;
            }
            return gcd(b, a%b);
        }
    }

    /**
     * 模拟法：超时
     */
    public static class Solution1 {
        public void rotate(int[] nums, int k) {
            int length = nums.length;
            if( length<=1 ) {
                return;
            }
            k = k % length;
            if( k==0 ) {
                return;
            }

            for(int count=0; count<k; count++) {
                int lastNum = nums[ length-1 ];
                for (int i=length-2; i>=0; i--) {
                    nums[i+1] = nums[i];
                }
                nums[0] = lastNum;
            }
            return;
        }
    }

    /**
     * 使用辅助数组
     */
    public static class Solution2 {
        private int[] aux;

        public void rotate(int[] nums, int k) {
            int N = nums.length;
            if( N<=1 ) {
                return;
            }
            k = k % N;
            if( k==0 ) {
                return;
            }

            aux = new int[N];
            for (int i=0; i<N; i++) {
                aux[i] = nums[i];
            }

            // [0, N-1-K] >> [K, N-1]
            for (int i=0,j=k; i<=N-k-1; i++,j++) {
                nums[j] = aux[i];
            }

            // [N-K, N-1] >> [0, K-1]
            for (int i=N-k,j=0; i<=N-1; i++,j++) {
                nums[j] = aux[i];
            }

            return;
        }
    }
}