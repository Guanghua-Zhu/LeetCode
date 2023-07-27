package com.aaron.LeetCode;

import java.util.*;
import java.util.stream.Collectors;

//给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4]
//解释：[4,9] 也是可通过的
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums1.length, nums2.length <= 1000 
// 0 <= nums1[i], nums2[i] <= 1000 
// 
//
// Related Topics 数组 哈希表 双指针 二分查找 排序 👍 795 👎 0


/**
 * 349, 两个数组的交集
 * @author Aaron Zhu
 * @date 2023-7-27
 */
public class IntersectionOfTwoArrays_349{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = Arrays.stream( nums1 )
                .boxed()
                .collect(Collectors.toSet());

            return Arrays.stream( nums2 )
                .distinct()
                .filter( set::contains )
                .toArray();
        }
    }

    public static class Solution1 {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<>(2000);
            for (int num : nums1) {
                set.add( num );
            }

            ArrayList<Integer> list = new ArrayList<>();
            for (int num : nums2) {
                list.add(num);
            }

            list.retainAll( set );
            set.clear();
            set.addAll( list );

            return set.stream()
                .mapToInt( Integer::valueOf )
                .toArray();
        }
    }
}