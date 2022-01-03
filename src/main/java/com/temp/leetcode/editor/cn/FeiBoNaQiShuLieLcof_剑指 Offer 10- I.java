package com.temp.leetcode.editor.cn;

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
 * @author Aaron Zhu
 * @date 2022-1-2
 */
public class FeiBoNaQiShuLieLcof_剑指 Offer 10- I{
  public static void main(String[] args) {
       Solution solution = new Solution();

  }
}

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 自底向上进行迭代
 */
class Solution {
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

//leetcode submit region end(Prohibit modification and deletion)


/**
 * 自顶向下进行递归
 */
class Solution1 {

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
