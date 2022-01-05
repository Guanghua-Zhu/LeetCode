package com.aaron.剑指Offer2ndEdition;

//写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
//
//
//F(0) = 0,   F(1) = 1
//F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
//
// 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//
//
//
// 示例 1：
//
//
//输入：n = 2
//输出：1
//
//
// 示例 2：
//
//
//输入：n = 5
//输出：5
//
//
//
//
// 提示：
//
//
// 0 <= n <= 100
//
// Related Topics 记忆化搜索 数学 动态规划
// 👍 279 👎 0


/**
 * 剑指offer·第二版: 10-I, 斐波那契数列
 * @author Aaron Zhu
 * @date 2022-1-2
 */
public class Offer_10_1 {

    public static void main(String[] args) {
    }

    /**
     * 自顶向下进行递归
     */
    public static class Solution1 {
        // 存储之前的计算结果
        private  Integer[] result;

        private Integer mod = 1000000007;

        public int fib(int n) {
            result = new Integer[n+1];
            int num = calc(n);
            return num;
        }

        private int calc(int n) {
            if( n==0 ) {
                return 0;
            } else if( n==1 ) {
                return 1;
            } else if( result[n]!=null ) {
                return result[n];
            }

            int num = calc(n-1) + calc(n-2);
            num %= mod;
            result[n] = num;
            return num;
        }
    }

    /**
     * 自底向上进行迭代
     */
    public static class Solution2 {
        private  Integer[] result;

        private Integer mod = 1000000007;

        public int fib(int n) {
            if(n==0 || n==1) {
                return n;
            }

            // f(2) = f(0) + f(1)
            // f(3) = f(1) + f(2)
            int firstNum = 0;
            int secondNum = 1;
            int num = 0;
            for(int i=2; i<=n; i++) {
                num = firstNum + secondNum;
                num %= mod;
                firstNum = secondNum;
                secondNum = num;
            }
            return num;
        }

    }

    /**
     * 矩阵快速幂
     */
    public static class Solution {
        private static int mod = 1000000007;

        // |  F(n)  | = |1, 1|^(n-1) * | F(1) |
        // | F(n-1) | = |1, 0|^(n-1) * | F(0) |
        /**
         * 转换矩阵
         */
        private static long[][] mat = new long[][]{ {1,1},{1,0} };

        public int fib(int n) {
            // F(0) = 0, F(1) = 1,
            if(n==0 || n==1) {
                return n;
            }
            long[][] resultMat = matQuickPower(n-1);
            // F(n) = resultMat[0][0] * F(1) + resultMat[0][1] * F(0)
            //      = resultMat[0][0] * 1 + resultMat[0][1] * 0
            //      = resultMat[0][0]
            return (int)resultMat[0][0];
        }

        /**
         * 矩阵快速幂
         */
        public long[][] matQuickPower(int exp) {
            // 矩阵乘法单位元: 单位矩阵
            long[][] result = new long[][]{{1,0},{0,1}};
            long[][] temp = mat;
            while ( exp!=0 ) {
                int lastBit = exp & 1;
                if( lastBit!=0 ) {
                    result = matMultiply(result, temp);
                }
                temp =  matMultiply(temp, temp);
                exp >>= 1;
            }
            return result;
        }

        /**
         * 矩阵乘法
         * @param a 2阶方阵
         * @param b 2阶方阵
         * @return
         */
        public static long[][] matMultiply(long[][] a, long[][] b) {
            long[][] result = new long[2][2];
            result[0][0] = ( (a[0][0]*b[0][0])%mod + (a[0][1]*b[1][0])%mod ) % mod ;
            result[0][1] = ( (a[0][0]*b[0][1])%mod + (a[0][1]*b[1][1])%mod ) % mod ;
            result[1][0] = ( (a[1][0]*b[0][0])%mod + (a[1][1]*b[1][0])%mod ) % mod ;
            result[1][1] = ( (a[1][0]*b[0][1])%mod + (a[1][1]*b[1][1])%mod ) % mod ;
            return result;
        }
    }

}
