package com.aaron.Leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 2299 👎 0

/**
 * LeetCode: 22, 括号生成
 * @author Aaron Zhu
 * @date 2022-1-18
 */
public class GenerateParentheses_22{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 回溯法
     */
    public static class Solution {
        /**
         * 左括号映射为 1
         */
        private final static List<Integer> leftBracket = Collections.singletonList(1);

        /**
         * 右括号映射为 -1
         */
        private final static List<Integer> rightBracket = Collections.singletonList(-1);

        private List<String> result;

        private int[] bracket;

        /**
         * 左、右括号的总数
         */
        private int num;

        public List<String> generateParenthesis(int n) {
            init(n);
            search(0);
            return result;
        }

        private void init(int n) {
            result = new LinkedList<>();
            num = 2 * n;
            bracket = new int[num];
        }

        private void search(int index) {
            if(index == num) {
                String bracketStr = convert();
                result.add( bracketStr );
                return;
            }

            List<Integer> brackets = getAvailableList(index);
            for(Integer e : brackets) {
                bracket[index] = e;     // (1)
                search(index+1);
                // 回溯法的这一步撤销操作, 实际上是可以省略的
                // 因为下一次遍历时, (1)处代码会通过覆盖的形式进行隐式撤销操作
                bracket[index] = 0;
            }
        }

        private List<Integer> getAvailableList(int index) {
            // 第一个括号只能是左括号
            if( index == 0 ) {
                return leftBracket;
            }

            int sum = Arrays.stream(bracket)
                .limit(index)
                .sum();
            // 前面的括号序列均配对成功, 则下一个括号只能是左括号
            if(sum==0) {
                return leftBracket;
            }

            // 此处说明sum一定是大于0, 故一定可以使用右括号
            List bracketList = new LinkedList( rightBracket );
            // 正确配对的括号对数， 比如()()()、((())), 其括号对数均是为3对
            int pairBracket = (index - sum) / 2;
            // 已使用左括号的数量 = 正确配对的括号对数 + 没有右括号配对的左括号的数量
            int leftBracketNum = pairBracket + sum;
            // 左括号没使用完
            if( leftBracketNum < num/2 ) {
                bracketList.addAll( leftBracket );
            }

            return bracketList;
        }

        /**
         * 把1、-1还原为左括号、右括号
         * @return
         */
        private String convert() {
            return Arrays.stream( bracket )
                .mapToObj(e -> {
                    if( e == 1 ) {
                        return "(";
                    } else if(e == -1) {
                        return ")";
                    }
                    return "";
                })
                .collect( Collectors.joining() );
        }
    }

}


