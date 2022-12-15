package com.temp.leetcode.editor.cn;

import java.util.*;

//给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。 
//
// '?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
// 
//
// 两个字符串完全匹配才算匹配成功。 
//
// 说明: 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。 
// 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "*"
//输出: true
//解释: '*' 可以匹配任意字符串。
// 
//
// 示例 3: 
//
// 输入:
//s = "cb"
//p = "?a"
//输出: false
//解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
// 
//
// 示例 4: 
//
// 输入:
//s = "adceb"
//p = "*a*b"
//输出: true
//解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
// 
//
// 示例 5: 
//
// 输入:
//s = "acdcb"
//p = "a*c?b"
//输出: false 
//
// Related Topics 贪心 递归 字符串 动态规划 👍 973 👎 0


/**
 * 44, 通配符匹配
 * @author Aaron Zhu
 * @date 2022-12-12
 */
public class WildcardMatching_44{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private int sSize;

    private int pSize;

    private int sIndex;

    private int pIndex;

    private boolean res;

    public boolean isMatch(String s, String p) {
        if( s==null || p==null ) {
            return false;
        }

        init(s,p);

        return res;
    }

    private void init(String s, String p) {
        sSize = s.length();
        pSize = p.length();
        sIndex = 0;
        pIndex = 0;
        res = false;
    }

    private void search(int sPos, int pPos) {
        if( sPos )
    }
}
//leetcode submit region end(Prohibit modification and deletion)
