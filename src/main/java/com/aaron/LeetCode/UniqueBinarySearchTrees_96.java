package com.aaron.LeetCode;

import java.util.*;

//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 1758 👎 0


/**
 * 96, 不同的二叉搜索树
 * @author Aaron Zhu
 * @date 2022-5-17
 */
public class UniqueBinarySearchTrees_96{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 记忆化
     */
    public static class Solution {
        public int numTrees(int n) {
            // F(n) = F(0)*F(n-1) + F(1)*F(n-2) + F(2)*F(n-3) + ··· + F(n-1)*F(0)
            Map<Integer, Integer> cache = new HashMap<>();
            int res = calc(n, cache);
            return res;
        }

        public int calc(int n, Map<Integer, Integer> cache) {
            if( n==0 ) {
                return 1;
            } else if( n==1 ) {
                return 1;
            } else if( n==2 ) {
                return 2;
            }

            if( cache.containsKey(n) ) {
                return cache.get(n);
            }

            int res = 0;
            for (int i=0; i<n; i++) {
                res += calc(i, cache) * calc(n-1-i, cache);
            }

            cache.put(n, res);
            return res;
        }
    }
}

