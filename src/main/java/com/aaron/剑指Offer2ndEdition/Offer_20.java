package com.aaron.剑指Offer2ndEdition;

//请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
//
// 数值（按顺序）可以分成以下几个部分： 
//
// 
// 若干空格 
// 一个 小数 或者 整数 
// （可选）一个 'e' 或 'E' ，后面跟着一个 整数 
// 若干空格 
// 
//
// 小数（按顺序）可以分成以下几个部分： 
//
// 
// （可选）一个符号字符（'+' 或 '-'） 
// 下述格式之一：
// 
// 至少一位数字，后面跟着一个点 '.' 
// 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字 
// 一个点 '.' ，后面跟着至少一位数字 
// 
// 
// 
//
// 整数（按顺序）可以分成以下几个部分： 
//
// 
// （可选）一个符号字符（'+' 或 '-'） 
// 至少一位数字 
// 
//
// 部分数值列举如下： 
//
// 
// ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"] 
// 
//
// 部分非数值列举如下： 
//
// 
// ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"] 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "0"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "e"
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：s = "."
//输出：false 
//
// 示例 4： 
//
// 
//输入：s = "    .1  "
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。 
// 
// Related Topics 字符串 
// 👍 285 👎 0


/**
 * 剑指 Offer 20, 表示数值的字符串
 * @author Aaron Zhu
 * @date 2022-2-9
 */
public class Offer_20 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 硬写……
     */
    public static class Solution {
    public boolean isNumber(String s) {
        if( s==null || s.length()==0 ) {
            return false;
        }
        // 移除前后空格
        s = removeBlank(s);
        if( s==null ) {
            return false;
        }
        int eCount = 0;
        for(char ch :s.toCharArray() ) {
            if( ch=='e' || ch=='E' ) {
                eCount++;
                if( eCount>1 ) {
                    return false;
                }
                continue;
            }
            if( ch=='+' || ch=='-' || ch=='.' ) {
                continue;
            }
            if( ch>='0' && ch<='9' ) {
                continue;
            }
            return false;
        }
        String[] array = s.split("[eE]", -1);
        // 检查第一部分
        boolean isValid = isInteger(array[0]) || isFloat(array[0]);
        if( !isValid ) {
            return false;
        }
        // 检查第二部分
        if( eCount!=0 ) {
            return isInteger(array[1]);
        }
        return true;
    }

    private String removeBlank(String s) {
        char[] chars = s.toCharArray();
        int startIndex = 0;
        while ( startIndex < chars.length ) {
            if( chars[startIndex] != ' ' ) {
                break;
            }
            startIndex++;
        }

        int endIndex = chars.length-1;
        while ( endIndex >=0 ) {
            if( chars[endIndex] != ' ' ) {
                break;
            }
            endIndex--;
        }

        if( startIndex==chars.length || endIndex==-1 ) {
            return null;
        }

        return s.substring(startIndex, endIndex+1);
    }

    /**
     * 是否为整数
     * @param num
     * @return
     */
    private boolean isInteger(String num) {
        if( num==null || num.length()==0 ) {
            return false;
        }
        char[] chars = num.toCharArray();
        // 存在符号位, 至少需要一位数字
        if( chars.length==1  && (chars[0]=='+' || chars[0] =='-') ) {
            return false;
        }

        for(int i=0; i<chars.length; i++) {
            char ch = chars[i];
            if ( i==0 && (ch=='+' || ch=='-') ) {
                continue;
            }
            if( ch>='0' && ch<='9' ) {
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * 是否为小数
     * @param num
     * @return
     */
    private boolean isFloat(String num) {
        if( num==null || num.length()==0 ) {
            return false;
        }

        char firstCh = num.charAt(0);
        // 存在符号位
        if( firstCh=='+' || firstCh=='-' ) {
            if( num.length() == 1 ) {
                // 至少需要一位数字
                return false;
            } else {
                // 移除符号位
                num = num.substring(1);
            }
        }

        int dotCount = 0;
        int digitCount = 0;
        for (char ch: num.toCharArray()) {
            // 统计小数点次数
            if( ch == '.' ) {
                dotCount++;
                continue;
            }
            if( ch>='0' && ch<='9' ) {
                digitCount++;
                continue;
            }
            return false;
        }

        if( dotCount!=1 || digitCount==0 ) {
            return false;
        }

        return true;
    }

}
}


