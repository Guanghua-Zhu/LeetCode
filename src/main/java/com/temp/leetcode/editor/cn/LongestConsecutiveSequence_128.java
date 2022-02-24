package com.temp.leetcode.editor.cn;

import com.aaron.Algo.UnionFind;
import com.sun.org.apache.xpath.internal.objects.XNull;

import java.util.*;

//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 
//
// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 
//
// 示例 2： 
//
// 
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// 
// Related Topics 并查集 数组 哈希表 
// 👍 1116 👎 0


/**
 * 128, 最长连续序列
 * @author Aaron Zhu
 * @date 2022-2-24
 */
public class LongestConsecutiveSequence_128{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 并查集
 */
class Solution {
    public int longestConsecutive(int[] nums) {

    }


    public static class UnionFind {
        private Map<Integer, Integer> parent;

        private Map<Integer, Integer> rank;

        public UnionFind(int[] nums) {
            parent = new HashMap<>();
            rank = new HashMap<>();
            for(int num : nums) {
                parent.put(num, num);
                rank.put(num, 1);
            }
        }

        public void union(int num) {

        }

        public Integer find(int num) {
            if( !parent.containsKey(num) ) {
                // 不存在num
                return null;
            }

            while ( !parent.get(num).equals(num) ) {


                num = parent.get(num);
            }
            return parent.get(num);

        }
    }
}


//leetcode submit region end(Prohibit modification and deletion)

/**
 * 哈希集合
 */
class Solution2 {
    public int longestConsecutive(int[] nums) {
        if(nums==null || nums.length==0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int maxLength = 1;
        for(int num : nums) {
            // 如果不是连续序列的起点, 则直接跳过。避免重复统计
            if( set.contains(num-1) ) {
                continue;
            }

            int currentLength = 0;
            while ( set.contains(num) ) {
                currentLength++;
                num++;
            }
            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }
}
