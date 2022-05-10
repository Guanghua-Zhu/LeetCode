package com.aaron.LeetCode;

import java.util.*;

//给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指在第 i 天之后，才会有更高的温度
//。如果气温在这之后都不会升高，请在该位置用 0 来代替。 
//
// 
//
// 示例 1: 
//
// 
//输入: temperatures = [73,74,75,71,69,72,76,73]
//输出: [1,1,4,2,1,1,0,0]
// 
//
// 示例 2: 
//
// 
//输入: temperatures = [30,40,50,60]
//输出: [1,1,1,0]
// 
//
// 示例 3: 
//
// 
//输入: temperatures = [30,60,90]
//输出: [1,1,0] 
//
// 
//
// 提示： 
//
// 
// 1 <= temperatures.length <= 10⁵ 
// 30 <= temperatures[i] <= 100 
// 
// Related Topics 栈 数组 单调栈 👍 1146 👎 0


/**
 * 739, 每日温度
 * @author Aaron Zhu
 * @date 2022-5-10
 */
public class DailyTemperatures_739{
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 单调递增栈
     */
    public static class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int size = temperatures.length;
            int[] res = new int[size];
            // 单调递增栈
            Deque<Integer> stack = new LinkedList<>();

            for(int i=size-1; i>=0; i--) {
                // 当前温度
                int curTem = temperatures[i];
                // 如果栈顶所对应的温度 不大于 当前温度, 就一直弹栈
                while ( !stack.isEmpty() && temperatures[stack.peek()] <= curTem ) {
                    stack.poll();
                }
                // 此时栈顶的位置索引 即为 右侧第一个比当前温度高的元素位置索引
                res[i] = stack.isEmpty() ? 0 : stack.peek()-i;
                // 当前温度的位置索引入栈
                stack.push(i);
            }

            return res;
        }
    }
}

