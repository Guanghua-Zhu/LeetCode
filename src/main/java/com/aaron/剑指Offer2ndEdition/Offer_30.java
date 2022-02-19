package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。 
//
// 
//
// 示例: 
//
// MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.min();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.min();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// 各函数的调用总次数不超过 20000 次 
// 
//
// 
//
// 注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/ 
// Related Topics 栈 设计 
// 👍 278 👎 0


/**
 * 剑指 Offer 30, 包含min函数的栈
 * @author Aaron Zhu
 * @date 2022-2-19
 */
public class Offer_30 {
    public static void main(String[] args) {
    }

    public static class MinStack {

        private LinkedList<Integer> stack;

        private LinkedList<Integer> min;

        /** initialize your data structure here. */
        public MinStack() {
            stack = new LinkedList<>();
            min = new LinkedList<>();
        }

        public void push(int x) {
            stack.offerLast(x);
            Integer lastMin = min.peekLast();
            if( lastMin==null || x<=lastMin ) {
                min.offerLast(x);
            }
        }

        public void pop() {
            Integer num = stack.pollLast();
            if( num!=null && num.equals(min.peekLast()) ) {
                min.pollLast();
            }
        }

        public int top() {
            return stack.peekLast();
        }

        public int min() {
            return min.peekLast();
        }
    }
}

