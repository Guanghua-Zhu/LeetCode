package com.aaron.LeetCode;

import java.util.*;

//给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。 
//
// 例如： 
//
// 
//A -> 1
//B -> 2
//C -> 3
//...
//Z -> 26
//AA -> 27
//AB -> 28 
//... 
//
// 
//
// 示例 1: 
//
// 
//输入: columnTitle = "A"
//输出: 1
// 
//
// 示例 2: 
//
// 
//输入: columnTitle = "AB"
//输出: 28
// 
//
// 示例 3: 
//
// 
//输入: columnTitle = "ZY"
//输出: 701 
//
// 
//
// 提示： 
//
// 
// 1 <= columnTitle.length <= 7 
// columnTitle 仅由大写英文组成 
// columnTitle 在范围 ["A", "FXSHRXW"] 内 
// 
//
// Related Topics 数学 字符串 👍 365 👎 0


/**
 * 171, Excel 表列序号
 * @author Aaron Zhu
 * @date 2023-1-21
 */
public class ExcelSheetColumnNumber_171{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    public static class Solution {
        public int titleToNumber(String columnTitle) {
            if( columnTitle==null || columnTitle.length()==0 ) {
                return 0;
            }

            char[] chars = columnTitle.toCharArray();
            int res = 0;
            int factor = 1;
            for (int i=chars.length-1; i>=0; i--) {
                res += (chars[i]-'A'+1) * factor;
                factor *= 26;
            }
            return res;
        }
    }

}