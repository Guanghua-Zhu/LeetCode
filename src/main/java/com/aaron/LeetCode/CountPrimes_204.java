package com.aaron.LeetCode;

import java.util.*;

//给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 10
//输出：4
//解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
// 
//
// 示例 2： 
//
// 
//输入：n = 0
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：n = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 5 * 10⁶ 
// 
//
// Related Topics 数组 数学 枚举 数论 👍 1077 👎 0


/**
 * 204, 计数质数
 * @author Aaron Zhu
 * @date 2023-7-17
 */
public class CountPrimes_204{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.countPrimes(10);
        System.out.println("gg");
    }

    /**
     * 埃氏筛
     */
    public static class Solution1 {
        public int countPrimes(int n) {
            if( n<2 ) {
                return 0;
            }

            int count = 0;
            int[] isPrime = new int[n];
            Arrays.fill(isPrime, 1);
            for (int i=2; i<n; i++) {
                // 如果i是质数的话，则其倍数一定不是质数
                if( isPrime[i]==1 ) {
                    count++;
                    for (long j=(long)i*i; j<n; j+=i) {
                        isPrime[(int)j] = 0;
                    }
                }
            }
            return count;
        }

        /**
         * 判定n是否为质数
         * @param n
         * @return
         */
        private boolean isPrime(int n) {
            for (int i=2; (long)i*(long)i <= n; i++) {
                if( n%i == 0 ) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 暴力解法
     */
    public static class Solution {
        /**
         * Key: num，Value: 比num小的质数总数
         */
        private static Map<Integer, Integer> counts = new HashMap<>();

        public int countPrimes(int n) {
            if( n<2 ) {
                return 0;
            } else if( counts.containsKey(n) ) {
                return counts.get(n);
            }

            int count = 0;
            for (int i=n-1; i>=2; i--) {
                if( isPrime(i) ) {
                    count++;
                }

                if( counts.containsKey(i) ) {
                    count += counts.get(i);
                    break;
                }
            }
            counts.put(n, count);
            return count;
        }

        /**
         * 判定n是否为质数
         * @param n
         * @return
         */
        private boolean isPrime(int n) {
            for (int i=2; (long)i*(long)i <= n; i++) {
                if( n%i == 0 ) {
                    return false;
                }
            }
            return true;
        }
    }
}
