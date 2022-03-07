package com.temp.leetcode.editor.cn;

import java.util.*;

//输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。 
//
// 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 13
//输出：6 
//
// 
//
// 限制： 
//
// 
// 1 <= n < 2^31 
// 
//
// 注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/ 
// Related Topics 递归 数学 动态规划 
// 👍 288 👎 0


/**
 * 剑指 Offer 43, 1～n 整数中 1 出现的次数
 * @author Aaron Zhu
 * @date 2022-3-7
 */
public class OneNzhengShuZhong1chuXianDeCiShuLcof_剑指 Offer 43{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countDigitOne(int n) {
        byte[] dp = new byte[n+1];
        int res = 0;
        for(int num=1; num<=n; num++) {
            int singleDigit = num%10;
            byte temp = (byte)(singleDigit==1 ? 1 : 0);
            temp += dp[num/10];
            dp[num] = temp;

            res += dp[num];
        }
        return res;
    }

    private byte calc(int num, byte[] cache) {
        if( cache[num]!=-1 ) {
            return cache[num];
        }

        if( num==0 ) {
            cache[num] = 0;
            return cache[num];
        }

        int singleDigit = num%10;
        byte res = (byte)(singleDigit==1 ? 1 : 0);
        res += calc(num/10, cache);
        cache[num] = res;
        return cache[num];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
