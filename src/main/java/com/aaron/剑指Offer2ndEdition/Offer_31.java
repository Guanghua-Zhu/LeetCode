package com.aaron.剑指Offer2ndEdition;

import java.util.*;

// 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序
// 假设压入栈的所有数字均不相等
// 例如，序列 {1,2,3,4,5} 是某栈的压栈序列
// 序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列
// 但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列
//
// 示例 1：
// 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
// 输出：true
// 解释：我们可以按以下顺序执行：
// push(1), push(2), push(3), push(4), pop() -> 4,
// push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
// 
// 示例 2：
// 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
// 输出：false
// 解释：1 不能在 2 之前弹出
// 
// 提示：
// 0 <= pushed.length == popped.length <= 1000
// 0 <= pushed[i], popped[i] < 1000 
// pushed 是 popped 的排列
// 注意：本题与主站 946 题相同：https://leetcode-cn.com/problems/validate-stack-sequences/
// Related Topics 栈 数组 模拟 
// 👍 273 👎 0


/**
 * 剑指 Offer 31, 栈的压入、弹出序列
 * @author Aaron Zhu
 * @date 2022-1-31
 */
public class Offer_31 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 遍历入栈列表
     */
    public static class Solution {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            if( (pushed==null||pushed.length==0) && (popped==null||popped.length==0) ) {
                return true;
            }

            if( pushed==null || pushed.length==0 || popped==null || popped.length==0
                || pushed.length!=popped.length ) {
                return false;
            }

            LinkedList<Integer> stack = new LinkedList<>();
            int popIndex = 0;
            for(Integer pushNum : pushed) {
                stack.addLast(pushNum);
                while ( !stack.isEmpty() && stack.peekLast().equals(popped[popIndex]) ) {
                    stack.removeLast();
                    popIndex++;
                }
            }

            boolean result = stack.isEmpty();
            return result;
        }
    }

    /**
     * 遍历出栈列表
     */
    public static class Solution1 {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            if( (pushed==null||pushed.length==0) && (popped==null||popped.length==0) ) {
                return true;
            }

            if( pushed==null || pushed.length==0 || popped==null || popped.length==0
                || pushed.length!=popped.length ) {
                return false;
            }

            LinkedList<Integer> stack = new LinkedList<>();
            int pushIndex = 0;
            for(Integer popNum : popped) {
                // 所弹出的元素已位于栈顶, 故可以直接弹出
                if( popNum.equals(stack.peekLast()) ) {
                    stack.removeLast();
                    continue;
                }

                boolean popOk = false;
                while ( pushIndex<pushed.length ) {
                    // 获取下一个入栈元素
                    Integer pushNum = pushed[pushIndex];
                    pushIndex++;
                    // 在入栈队列中找到出栈元素
                    if( pushNum.equals(popNum) ) {
                        popOk = true;
                        break;
                    } else {
                        // 继续入栈
                        stack.addLast( pushNum );
                    }
                }

                if( !popOk ) {
                    return false;
                }
            }
            return true;
        }
    }

}

