package com.temp.leetcode.editor.cn;

import com.aaron.剑指Offer2ndEdition.Offer_20;

import java.util.*;

//请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配
//是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "a*"
//输出: true
//解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3: 
//
// 输入:
//s = "ab"
//p = ".*"
//输出: true
//解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 示例 4: 
//
// 输入:
//s = "aab"
//p = "c*a*b"
//输出: true
//解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
// 
//
// 示例 5: 
//
// 输入:
//s = "mississippi"
//p = "mis*is*p*."
//输出: false 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。 
// 
//
// 注意：本题与主站 10 题相同：https://leetcode-cn.com/problems/regular-expression-matching/
// 
// Related Topics 递归 字符串 动态规划 
// 👍 335 👎 0


/**
 * 面试题19, 正则表达式匹配
 * @author Aaron Zhu
 * @date 2022-2-13
 */
//public class ZhengZeBiaoDaShiPiPeiLcof_面试题19{
public class offer {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean res = solution.isMatch("ab",".*");
        System.out.println("gg");
    }
}

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * NFA 非确定有限状态自动机
 */
class Solution {

    private static final Character epsilonCharacter = '+';

    private static final Character anyCharacter = '.';

    /**
     * 状态转移规则, 当前状态 + 输入类型 -> 下一个状态
     * 第一层Map, key: 当前状态
     * 第二层Map, key: 输入类型; value: 下一个状态
     */
    private Map<Integer, Map<Character, Integer>> transferRule;

    private int initState = -1;

    private int endState;

    public boolean isMatch(String s, String p) {
        if( s==null || s=="" || p==null || p=="" ) {
            return false;
        }

        buildNFA(p);
        boolean res = search(s.toCharArray(), 0, initState);
        return res;
    }

    /**
     * 构建NFA
     * @param p
     */
    private void buildNFA(String p) {
        transferRule = new HashMap<>();
        int state = initState;
        char[] chars = p.toCharArray();
        for(int index=0; index<chars.length; index++) {
            char ch = chars[index];
            if( (ch>='a' && ch<='z') || ch=='.' ) {
                Map<Character, Integer> map = transferRule.computeIfAbsent(state, key->new HashMap<>() );
                map.put(ch, state+1);
                state++;
            } else if ( ch=='*' ) {
                // 获取可以进行重复匹配的字符
                char repeatChar = chars[index-1];
                Map<Character, Integer> lastMap = transferRule.get(state-1);
                // 将 state-1状态 -> state状态 的转移条件由 重复字符 改为 空转移
                lastMap.remove( repeatChar );
                lastMap.put( epsilonCharacter, state );

                // 建立 state状态 -> state状态 的重复转移, 其中转移条件即为 重复字符
                Map<Character, Integer> map = transferRule.computeIfAbsent( state , key->new HashMap<>() );
                map.put(repeatChar, state);
            }
        }

        endState = state;
    }

    private boolean search(char[] chars, int index, int state) {
        if( index > chars.length-1 && state == endState) {
            return true;
        }

        if( index > chars.length-1 ) {
            return false;
        } else if( !transferRule.containsKey(state) ) {
            return false;
        }

        boolean res1 = false;
        boolean res2 = false;
        // 获取当前状态下的转移规则
        Map<Character, Integer> map = transferRule.get( state );
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            char inputType = entry.getKey();
            int nextState = entry.getValue();

            if( inputType==epsilonCharacter ) {
                res1 = search(chars, index, nextState );
            } else if( chars[index]==inputType || chars[index]==anyCharacter ) {
                res2 = search(chars, index+1, nextState );
            }
        }

        return res1 || res2;
    }

}


//leetcode submit region end(Prohibit modification and deletion)

/**
 * NFA 非确定有限状态自动机 + 回溯
 */
class Solution1 {
    private List<Character> state;

    private Set<Integer> repeatSet;

    private Set<Integer> epsilonSet;

    public boolean isMatch(String s, String p) {
        if( s==null || s=="" || p==null || p=="" ) {
            return false;
        }

        state = new ArrayList<>();
        repeatSet = new HashSet<>();
        epsilonSet = new HashSet<>();

        // 构建NFA
        int index = 0;
        for(char ch : p.toCharArray()) {
            if( (ch>='a' && ch<='z') || ch=='.' ) {
                state.add(ch);
                index++;
            } else if( ch=='*' ) {
                // *号前面的字符可以重复
                repeatSet.add( index-1 );
                // 从 state[index-2]状态 到 state[index-1]状态 为 空转移
                epsilonSet.add( index-1);
            }
        }

        char[] chars = s.toCharArray();
        boolean res = dfs(chars, -1, -1,0);
        return res;
    }

    private boolean dfs(char[] chars, int charsIndex, int stateIndex, int opsType) {
        if( charsIndex > chars.length-1 && stateIndex > state.size()-1 ) {
            return true;
        }

        if( charsIndex > chars.length-1 || stateIndex > state.size()-1 ) {
            return false;
        }

        // 通过 空转移 进入的
        if( opsType==1 ) {
            if( !epsilonSet.contains(stateIndex) ) {
                return false;
            }
        } else if ( opsType==2 ) { // 通过 匹配下一个状态 进入的
            if( epsilonSet.contains(stateIndex) ) {
                return false;
            }
            if( chars[charsIndex]!=state.get(stateIndex) && state.get(stateIndex)!='.' ) {
                return false;
            }
        } else if( opsType==3 ) {   // 通过 重复匹配当前状态 进入的
            if( !repeatSet.contains(stateIndex) ) {
                return false;
            }
            if( chars[charsIndex]!=state.get(stateIndex) && state.get(stateIndex)!='.' ) {
                return false;
            }
        }

        return dfs(chars, charsIndex, stateIndex+1, 1)                 // 空转移
            || dfs(chars, charsIndex+1, stateIndex+1, 2)   // 匹配下一个状态
            || dfs(chars, charsIndex+1, stateIndex, 3);               // 重复匹配当前状态
    }
}
