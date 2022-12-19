package com.temp.leetcode.editor.cn;

import java.util.*;

//实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xⁿ ）。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100
// 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
// 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// n 是一个整数 
// -104 <= xⁿ <= 104 
// 
//
// Related Topics 递归 数学 👍 1086 👎 0


/**
 * 50, Pow(x, n)
 * @author Aaron Zhu
 * @date 2022-12-19
 */
public class PowxN_50{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double myPow(double x, int n) {
        boolean signFlag = false;   // 负号标志位
        if( n==0 ) {
            return 1;
        } else if( x ==1 ) {
            return 1;
        }
        else  if( n<0 ) {
            signFlag = true;
            n = -1 * n;
        }

        double ans = 1;
        double temp = x;
        while ( n!=0 ) {
            if( (n&1) ==1 ) {
                ans *= temp;
            }
            temp *= temp;
            n >>= 1;
        }

        return signFlag ? 1/ans : ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
