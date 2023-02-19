package com.aaron.LeetCode;

import java.util.*;

//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[
//b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）： 
//
// 
// 0 <= a, b, c, d < n 
// a、b、c 和 d 互不相同 
// nums[a] + nums[b] + nums[c] + nums[d] == target 
// 
//
// 你可以按 任意顺序 返回答案 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 
//
// Related Topics 数组 双指针 排序 👍 1454 👎 0


/**
 * 18, 四数之和
 * @author Aaron Zhu
 * @date 2022-12-24
 */
public class FourSum_18{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            int size = nums.length;
            Arrays.sort( nums );

            for (int i=0; i<size-3; i++) {
                if( i>0 && nums[i]==nums[i-1] ) {
                    continue;
                }

                if( (long)nums[i]+(long)nums[i+1]+(long)nums[i+2]+(long)nums[i+3] > target ) {
                    break;
                }
                if( (long)nums[i]+(long)nums[size-1]+(long)nums[size-2]+(long)nums[size-3] < target ) {
                    continue;
                }


                for (int j=i+1; j<size-2; j++) {
                    if( j>i+1 && nums[j]==nums[j-1] ) {
                        continue;
                    }

                    if( (long)nums[i]+(long)nums[j]+(long)nums[j+1]+(long)nums[i+2] > target ) {
                        break;
                    }
                    if( (long)nums[i]+(long)nums[j]+(long)nums[size-1]+(long)nums[size-2] < target ) {
                        continue;
                    }


                    int left = j+1;
                    int right = size-1;
                    while (left < right) {
                        long sum = (long)nums[i] + (long)nums[j] + (long)nums[left] + (long)nums[right];
                        if( sum == (long)target ) {
                            res.add( Arrays.asList(nums[i], nums[j], nums[left], nums[right]) );
                            while ( left < right && nums[left+1]== nums[left] ) {
                                left++;
                            }
                            left++;
                            while ( left < right && nums[right-1]== nums[right] ) {
                                right--;
                            }
                            right--;
                        } else if ( sum < (long)target ) {
                            while ( left < right && nums[left+1]== nums[left] ) {
                                left++;
                            }
                            left++;
                        } else if ( sum > (long)target ) {
                            while ( left < right && nums[right-1]== nums[right] ) {
                                right--;
                            }
                            right--;
                        }
                    }
                }
            }

            return res;
        }
    }

    public static class Solution1 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            int size = nums.length;
            Arrays.sort( nums );

            for (int i=0; i<size-3; i++) {
                if( i>0 && nums[i]==nums[i-1] ) {
                    continue;
                }

                for (int j=i+1; j<size-2; j++) {
                    if( j>i+1 && nums[j]==nums[j-1] ) {
                        continue;
                    }

                    for (int k=j+1; k<size-1; k++) {
                        if( k>j+1 && nums[k]==nums[k-1] ) {
                            continue;
                        }

                        for (int l=k+1; l<size; l++) {
                            if( l>k+1 && nums[l]==nums[l-1] ) {
                                continue;
                            }
                            long sum = (long)nums[i] + (long)nums[j] + (long)nums[k] + (long)nums[l];
                            if( sum == (long)(target) ) {
                                res.add( Arrays.asList(nums[i], nums[j], nums[k], nums[l]) );
                            }
                        }
                    }
                }
            }

            return res;
        }
    }

}