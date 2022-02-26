package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。 
//
// 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。 
//
// 
//
// 示例 1： 
//
// 输入：target = 9
//输出：[[2,3,4],[4,5]]
// 
//
// 示例 2： 
//
// 输入：target = 15
//输出：[[1,2,3,4,5],[4,5,6],[7,8]]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= target <= 10^5 
// 
//
// 
// Related Topics 数学 双指针 枚举 
// 👍 377 👎 0


/**
 * 剑指 Offer 57 - II, 和为s的连续正数序列
 * @author Aaron Zhu
 * @date 2022-2-26
 */
public class Offer_57_2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 双指针法
     */
    public static class Solution {
        public int[][] findContinuousSequence(int target) {
            List<int[]> list = new LinkedList<>();
            int max = target/2 + 1;
            int l = 1;
            int r = l + 1;
            while (l<r && r<=max) {
                int sum = calcSum(l,r);
                if (sum < target) {
                    r++;
                } else if (sum > target) {
                    l++;
                } else {
                    int[] subRes = buildSubRes(l,r);
                    list.add( subRes );
                    l++;
                }
            }

            return list.toArray( new int[0][] );
        }

        private int calcSum(int l, int r) {
            return (l+r) * (r-l+1) / 2;
        }

        private int[] buildSubRes(int l, int r) {
            int size = r-l+1;
            int[] array = new int[size];
            int index = 0;
            for (int num=l; num<=r; num++) {
                array[index] = num;
                index++;
            }
            return array;
        }
    }

    /**
     * 暴力枚举
     */
    public static class Solution1 {
        public int[][] findContinuousSequence(int target) {
            List<int[]> list = new LinkedList<>();
            int max = target/2 + 1;
            for (int i=1; i<=max; i++) {
                int sum = 0;
                for (int j=i; j<=max; j++) {
                    sum +=j;
                    if( sum==target ) {
                        int[] subRes = buildSubRes(i,j);
                        list.add( subRes );
                        break;
                    } else if( sum>target ) {
                        break;
                    }
                }
            }

            return list.toArray( new int[0][] );
        }

        private int[] buildSubRes(int l, int r) {
            int size = r-l+1;
            int[] array = new int[size];
            int index = 0;
            for (int num=l; num<=r; num++) {
                array[index] = num;
                index++;
            }
            return array;
        }
    }
}

