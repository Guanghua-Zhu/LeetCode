package com.temp.leetcode.editor.cn;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。 
//
// 
//
// 示例 1: 
//
// 输入: [10,2]
//输出: "102" 
//
// 示例 2: 
//
// 输入: [3,30,34,5,9]
//输出: "3033459" 
//
// 
//
// 提示: 
//
// 
// 0 < nums.length <= 100 
// 
//
// 说明: 
//
// 
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数 
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0 
// 
// Related Topics 贪心 字符串 排序 
// 👍 380 👎 0


/**
 * @author Aaron Zhu
 * @date 2022-2-16
 */
public class BaShuZuPaiChengZuiXiaoDeShuLcof_剑指 Offer 45{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)

import java.math.BigDecimal;

class Solution {
    public String minNumber(int[] nums) {
        List<Integer> numList = Arrays.stream(nums)
            .boxed()
            .sorted( (num1, num2)->compare(num1,num2) )
            .collect(Collectors.toList());



    }

    private int compare(Integer num1, Integer num2) {
        String num1Str = num1.toString();
        String num2Str = num2.toString();
        int num1Size = num1Str.length();
        int num2Size = num2Str.length();
        int maxSize = Math.max(num1Size, num2Size);

        int result = num1Str.charAt(0) - num2Str.charAt(0);
        if( result!=0 ) {
            return result;
        }

        char firstNum = num1Str.charAt(0);
        char num1Ch;
        char num2Ch;
        for(int i=0; i<maxSize; i++) {
            if( i<num1Size ) {
                num1Ch = num1Str.charAt(i);
            } else {
                num1Ch = firstNum;
            }

            if( i<num2Size ) {
                num2Ch = num2Str.charAt(i);
            } else {
                num2Ch = firstNum;
            }

            result = num1Ch - num2Ch;
            if( result!=0 ) {
                break;
            }
        }

        return result;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
