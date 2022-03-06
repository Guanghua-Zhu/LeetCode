package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
// 
//
// 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。 
//
// 
//
// 示例 1： 
//
// 
//输入: n = 5, m = 3
//输出: 3
// 
//
// 示例 2： 
//
// 
//输入: n = 10, m = 17
//输出: 2
// 
//
// 
//
// 限制： 
//
// 
// 1 <= n <= 10^5 
// 1 <= m <= 10^6 
// 
// Related Topics 递归 数学 
// 👍 546 👎 0


/**
 * 剑指 Offer 62, 圆圈中最后剩下的数字
 * @author Aaron Zhu
 * @date 2022-2-27
 */
public class Offer_62 {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int res = solution.lastRemaining(4,2);
        System.out.println("gg");
    }

    /**
     * 数学法
     */
    public static class Solution {
        public int lastRemaining(int n, int m) {
            int res = 0;
            for(int i=2; i<=n; i++) {
                res = (res+m) % i;
            }
            return res;
        }
    }

    /**
     * 链表模拟法: 非递归版本
     */
    public static class Solution2 {
        public int lastRemaining(int n, int m) {
            List<Integer> list = new ArrayList<>();
            for(int i=0; i<n; i++) {
                list.add(i);
            }

            int index = 0;
            while ( list.size()>1 ) {
                int removeIndex = (index + m -1) % list.size();
                list.remove( removeIndex );
                index = removeIndex;
            }

            return list.get(0);
        }
    }

    /**
     * 链表模拟法：递归版本
     */
    public static class Solution1 {
        public int lastRemaining(int n, int m) {
            List<Integer> list = new ArrayList<>();
            for(int i=0; i<n; i++) {
                list.add(i);
            }
            remove(m, list, 0);
            return list.get(0);
        }

        private void remove(int m, List<Integer> list, int startIndex) {
            if( list.size()==1 ) {
                return;
            }

            int removeIndex = (startIndex + m -1) % list.size();
            list.remove(removeIndex);
            remove(m, list, removeIndex);
        }
    }

}

