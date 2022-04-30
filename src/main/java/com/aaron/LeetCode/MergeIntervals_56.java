package com.aaron.LeetCode;

import java.util.*;

//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 104 
// 
// Related Topics 数组 排序 
// 👍 1440 👎 0


/**
 * 56, 合并区间
 * @author Aaron Zhu
 * @date 2022-4-22
 */
public class MergeIntervals_56{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] a = new int[][]{{1,4}, {0,4}};
        solution.merge(a);
        System.out.println("gg");
    }

    public static class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]) );
            List<int[]> mergeList = new ArrayList<>();
            for (int i=0; i<intervals.length; i++) {
                int L = intervals[i][0];
                int R = intervals[i][1];

                if ( mergeList.size()==0 || mergeList.get( mergeList.size()-1 )[1] < L ) {
                    mergeList.add( new int[]{L,R} );
                } else {
                    mergeList.get( mergeList.size()-1 )[1] = Math.max(mergeList.get( mergeList.size()-1 )[1], R);
                }
            }

            return mergeList.toArray( new int[0][] );
        }
    }
}

