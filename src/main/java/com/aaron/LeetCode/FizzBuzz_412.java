package com.aaron.LeetCode;

import java.util.*;

//给你一个整数 n ，找出从 1 到 n 各个整数的 Fizz Buzz 表示，并用字符串数组 answer（下标从 1 开始）返回结果，其中： 
//
// 
// answer[i] == "FizzBuzz" 如果 i 同时是 3 和 5 的倍数。 
// answer[i] == "Fizz" 如果 i 是 3 的倍数。 
// answer[i] == "Buzz" 如果 i 是 5 的倍数。 
// answer[i] == i （以字符串形式）如果上述条件全不满足。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["1","2","Fizz"]
// 
//
// 示例 2： 
//
// 
//输入：n = 5
//输出：["1","2","Fizz","4","Buzz"]
// 
//
// 示例 3： 
//
// 
//输入：n = 15
//输出：["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","1
//4","FizzBuzz"] 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 10⁴ 
// 
//
// Related Topics 数学 字符串 模拟 👍 252 👎 0


/**
 * 412, Fizz Buzz
 * @author Aaron Zhu
 * @date 2022-12-8
 */
public class FizzBuzz_412{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public List<String> fizzBuzz(int n) {
            List<String> list = new ArrayList<>();
            int num3 = 0;
            int num5 = 0;
            for (int i=1; i<=n; i++) {
                boolean b3 = num3++%3 == 2;
                boolean b5 = num5++%5 == 4;
                if ( b3 && b5 ) {
                    list.add("FizzBuzz");
                } else if ( b3 ) {
                    list.add( "Fizz" );
                } else if ( b5 ) {
                    list.add( "Buzz" );
                } else {
                    list.add( String.valueOf(i) );
                }
            }
            return list;
        }
    }

}
