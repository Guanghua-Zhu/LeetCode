package com.temp.leetcode.editor.cn;

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
        int[][] a = new int[][]{{11,32}, {11,22}};
        solution.merge(a);
        System.out.println("gg");
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        if( intervals.length <=1 ) {
            return intervals;
        }

        List<int[]> list = new ArrayList<>();
        list.addAll( Arrays.asList(intervals) );
        list.sort( (o1,o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        List<int[]> res = new ArrayList<>();
        int resIndex = -1;
        int[] prev = null;
        for (int i=1; i<intervals.length; i++) {
            int[] cur = intervals[i];
            int[] tempRes = null;

            // 区间重叠
            if( prev !=null && prev[1]+1 >= cur[0] ) {
                tempRes = new int[]{prev[0], Math.max(prev[0], cur[1])};
                res.set(resIndex, tempRes);
            } else {
                resIndex++;
                tempRes = cur;
                res.add(resIndex, tempRes);
            }

            prev = tempRes;
        }

        System.out.println("gg");
        return res.toArray( new int[0][] );
    }

}
//leetcode submit region end(Prohibit modification and deletion)
