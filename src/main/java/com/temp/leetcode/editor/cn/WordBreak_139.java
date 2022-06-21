package com.temp.leetcode.editor.cn;

import java.util.*;

//给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。 
//
// 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
// 
//
// 示例 2： 
//
// 
//输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
//     注意，你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 
//输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 300 
// 1 <= wordDict.length <= 1000 
// 1 <= wordDict[i].length <= 20 
// s 和 wordDict[i] 仅有小写英文字母组成 
// wordDict 中的所有字符串 互不相同 
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 👍 1655 👎 0


/**
 * 139, 单词拆分
 * @author Aaron Zhu
 * @date 2022-6-21
 */
public class WordBreak_139{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private Set<String> words;

    private String str;

    private boolean res;


    public boolean wordBreak(String s, List<String> wordDict) {
        init(s, wordDict);
        search(0, 0);
        return res;
    }

    private void init(String s, List<String> wordDict) {
        words = new HashSet<>( wordDict );
        str = s;
        res = false;
    }

    private void search(int startIndex, int endIndex) {
        if( res==true ) {
            return;
        } else if( startIndex>=str.length() ) {
            res = true;
            return;
        }

        String subStr = str.substring(startIndex, endIndex+1);
        if( words.contains(subStr) ) {
            search(startIndex+1, startIndex+1);
        }

        if( endIndex+1 < str.length() ) {
            search(startIndex, endIndex+1);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
