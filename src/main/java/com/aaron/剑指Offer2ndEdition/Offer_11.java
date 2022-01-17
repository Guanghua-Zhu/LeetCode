package com.aaron.剑指Offer2ndEdition;

//把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 
//
// 给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。
// 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为1。
//
// 示例 1： 
//
// 
//输入：[3,4,5,1,2]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：[2,2,2,0,1]
//输出：0
// 
//
// 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sor
//ted-array-ii/ 
// Related Topics 数组 二分查找 
// 👍 481 👎 0


/**
 * 剑指offer·第二版: 11, 旋转数组的最小数字
 * @author Aaron Zhu
 * @date 2022-1-10
 */
public class Offer_11{
    
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
    }

    public static class Solution1 {
        public int minArray(int[] numbers) {
            int min = numbers[0];
            int last = numbers[0];

            for(int current : numbers) {
                if( current < last ) {
                    min = current;
                    break;
                }
                last = current;
            }
            return min;
        }
    }

    public static class Solution2 {
        public int minArray(int[] numbers) {
            int left = 0;
            int right = numbers.length-1;
            int mid;

            // 当left = right 时退出循环, 此时left所执向元素即为最小值
            while ( left<right ) {
                mid = left + (right-left)/2;
                if( numbers[mid]>numbers[right] ) {
                    // 因为mid位置的元素肯定不是最小元素, 因为right位置比其大
                    // 故搜索区间为[m+1, right]
                    left = mid + 1;
                } else if( numbers[mid]<numbers[right] ) {
                    // 因为mid位置的元素也有可能是最小元素, 故搜索区间为[left, mid]
                    right = mid;
                } else if( numbers[mid]==numbers[right] ) {
                    right--;
                }
            }
            return numbers[left];
        }
    }

}
