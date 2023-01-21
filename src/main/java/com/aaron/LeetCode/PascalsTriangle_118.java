package com.aaron.LeetCode;

import java.util.*;

//给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。 
//
// 在「杨辉三角」中，每个数是它左上方和右上方的数的和。 
//
// 
//
// 
//
// 示例 1: 
//
// 
//输入: numRows = 5
//输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
// 
//
// 示例 2: 
//
// 
//输入: numRows = 1
//输出: [[1]]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= numRows <= 30 
// 
//
// Related Topics 数组 动态规划 👍 902 👎 0


/**
 * 118, 杨辉三角
 * @author Aaron Zhu
 * @date 2023-1-21
 */
public class PascalsTriangle_118{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        private static Map<Integer, List<Integer>> cache;

        static {
            cache = new HashMap<>();
            cache.put(1, Arrays.asList(1) );
            cache.put(2, Arrays.asList(1,1) );
        }


        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> res = new ArrayList<>();
            for (int i=1; i<=numRows; i++) {
                if ( cache.containsKey(i) ) {
                    res.add( cache.get(i) );
                } else {
                    List<Integer> cur = calc(i);
                    res.add(cur);
                    cache.put(i, cur);
                }
            }
            return res;
        }

        private List<Integer> calc(int n) {
            List<Integer> last = cache.get(n-1);
            List<Integer> cur = new ArrayList<>();

            cur.add(1);
            for (int i=0; i<n-2; i++) {
                cur.add( last.get(i)+last.get(i+1) );
            }
            cur.add(1);
            return cur;
        }

    }

}