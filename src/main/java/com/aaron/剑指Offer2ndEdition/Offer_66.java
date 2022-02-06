package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[
//i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。 
//
// 
//
// 示例: 
//
// 
//输入: [1,2,3,4,5]
//输出: [120,60,40,30,24] 
//
// 
//
// 提示： 
//
// 
// 所有元素乘积之和不会溢出 32 位整数 
// a.length <= 100000 
// 
// Related Topics 数组 前缀和 
// 👍 192 👎 0


/**
 * 剑指 Offer 66, 构建乘积数组
 * @author Aaron Zhu
 * @date 2022-2-6
 */
public class Offer_66 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int[] constructArr(int[] a) {
            if( a==null || a.length==0 ) {
                return new int[0];
            }

            int[] res1 = new int[a.length];
            for (int i=0; i<a.length; i++) {
                if( i==0 ) {
                    res1[0] = 1;
                } else {
                    res1[i] = res1[i-1] * a[i-1];
                }
            }

            int[] res2 = new int[a.length];
            for(int i=a.length-1; i>=0; i--) {
                if( i==a.length-1 ) {
                    res2[a.length-1] = 1;
                } else {
                    res2[i] = res2[i+1] * a[i+1];
                }
            }

            int res[] = new int[a.length];
            for (int i=0; i<a.length; i++) {
                res[i] = res1[i] * res2[i];
            }

            return res;
        }
    }

}


