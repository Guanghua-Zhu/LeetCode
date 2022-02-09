package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。 
//
// 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 
// 👍 275 👎 0


/**
 * 剑指 Offer 49, 丑数
 * @author Aaron Zhu
 * @date 2022-2-9
 */
public class Offer_49 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
    public int nthUglyNumber(int n) {
        if(n==1) {
            return 1;
        }

        Queue<Long> minHeap = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        minHeap.offer(1l);
        set.add(1l);

        long res = 1;
        while (n>0) {
            res = minHeap.poll();
            long[] nextUglyNums = new long[]{2*res, 3*res, 5*res};
            for (long uglyNum : nextUglyNums ) {
                if( set.add(uglyNum) ) {
                    minHeap.offer( uglyNum );
                }
            }
            n--;
        }

        return (int)res;
    }
}

}

