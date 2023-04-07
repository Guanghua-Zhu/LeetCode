package com.aaron.LeetCode;

import java.util.*;

//编写一个算法来判断一个数 n 是不是快乐数。 
//
// 「快乐数」 定义为： 
//
// 
// 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。 
// 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。 
// 如果这个过程 结果为 1，那么这个数就是快乐数。 
// 
//
// 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 19
//输出：true
//解释：
//1² + 9² = 82
//8² + 2² = 68
//6² + 8² = 100
//1² + 0² + 0² = 1
// 
//
// 示例 2： 
//
// 
//输入：n = 2
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2³¹ - 1 
// 
//
// Related Topics 哈希表 数学 双指针 👍 1271 👎 0


/**
 * 202, 快乐数
 * @author Aaron Zhu
 * @date 2023-4-7
 */
public class HappyNumber_202{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        private static final Set<Long> happySet = new HashSet<>();

        private static final Set<Long> noHappySet = new HashSet<>();

        static {
            happySet.addAll( Arrays.asList(1L) );
            noHappySet.addAll( Arrays.asList(0L, 2L, 3L, 4L) );
        }

        public boolean isHappy(int n) {
            if( happySet.contains( (long)n ) ) {
                return true;
            } else if( noHappySet.contains( (long)n ) ) {
                return false;
            }

            Set<Long> tempSet = new HashSet<>();
            long num = n;
            while ( true ) {
                tempSet.add( num );
                num = calc(num);
                if( noHappySet.contains(num) ) {
                    noHappySet.addAll( tempSet );
                    tempSet.clear();
                    return false;
                } else if( happySet.contains(num) ) {
                    happySet.addAll(tempSet);
                    tempSet.clear();
                    return true;
                } else if( num==1 ) {
                    return true;
                }
            }
        }

        private long calc(long num) {
            long res = 0;
            while ( num !=0 ) {
                long temp = num%10;  // 取个位数
                num = num/10;       // 移除个位
                res += temp*temp;
            }
            return res;
        }

    }
}

