package com.aaron.LeetCode;

import java.util.*;
import java.util.stream.Collectors;

//给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现
//次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2,2]
// 
//
// 示例 2: 
//
// 
//输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[4,9] 
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
// 
//
// 进阶： 
//
// 
// 如果给定的数组已经排好序呢？你将如何优化你的算法？ 
// 如果 nums1 的大小比 nums2 小，哪种方法更优？ 
// 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？ 
// 
//
// Related Topics 数组 哈希表 双指针 二分查找 排序 👍 987 👎 0


/**
 * 350, 两个数组的交集 II
 * @author Aaron Zhu
 * @date 2023-7-27
 */
public class IntersectionOfTwoArraysIi_350{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            Map<Integer, Integer> map1 = new HashMap<>();
            for (int num : nums1) {
                map1.put(num, map1.getOrDefault(num,0)+1);
            }

            List<Integer> res = new ArrayList<>();
            for (int num : nums2) {
                int count = map1.getOrDefault(num, 0);
                if( count>0 ) {
                    map1.put(num, count-1);
                    res.add(num);
                }
            }

            return res.stream()
                .mapToInt( Integer::valueOf )
                .toArray();
        }
    }

    public static class Solution1 {
        public int[] intersect(int[] nums1, int[] nums2) {
            Set<Integer> set1 = new HashSet<>();
            Map<Integer, Integer> map1 = new HashMap<>();
            for (int num : nums1) {
                set1.add(num);
                map1.put(num, map1.getOrDefault(num,0)+1);
            }

            Set<Integer> set2 = new HashSet<>();
            Map<Integer, Integer> map2 = new HashMap<>();
            for (int num : nums2) {
                set2.add(num);
                map2.put(num, map2.getOrDefault(num,0)+1);
            }

            set1 = set1.stream()
                .filter( set2::contains )
                .collect(Collectors.toSet());

            List<Integer> res = new ArrayList<>();
            for (int num : set1) {
                int count = Math.min( map1.get(num), map2.get(num) );
                for (int i=0; i<count; i++) {
                    res.add(num);
                }
            }

            return res.stream()
                .mapToInt(Integer::valueOf)
                .toArray();
        }
    }
}