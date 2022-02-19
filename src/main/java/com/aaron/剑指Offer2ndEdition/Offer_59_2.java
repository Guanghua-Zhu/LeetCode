package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都
//是O(1)。 
//
// 若队列为空，pop_front 和 max_value 需要返回 -1 
//
// 示例 1： 
//
// 输入: 
//["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
//[[],[1],[2],[],[],[]]
//输出: [null,null,null,2,1,2]
// 
//
// 示例 2： 
//
// 输入: 
//["MaxQueue","pop_front","max_value"]
//[[],[],[]]
//输出: [null,-1,-1]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= push_back,pop_front,max_value的总操作数 <= 10000 
// 1 <= value <= 10^5 
// 
// Related Topics 设计 队列 单调队列 
// 👍 317 👎 0


/**
 * 剑指 Offer 59 - II, 队列的最大值
 * @author Aaron Zhu
 * @date 2022-2-19
 */
public class Offer_59_2 {
    public static void main(String[] args) {
    }

    /**
     * 单调队列: 单调递减队列
     */
    public static class MaxQueue {

        private Queue<Integer> queue;

        private LinkedList<Integer> max;

        public MaxQueue() {
            queue = new LinkedList<>();
            max = new LinkedList<>();
        }

        public int max_value() {
            if (max.peekFirst()==null) {
                return -1;
            } else {
                return max.peekFirst();
            }
        }

        public void push_back(int value) {
            queue.offer( value );
            while( max.peekLast()!=null && value > max.peekLast() ) {
                max.pollLast();
            }
            max.offerLast( value );
        }

        public int pop_front() {
            if( queue.peek()==null ) {
                return -1;
            }

            if ( queue.peek().equals( max.peekFirst() )) {
                max.pollFirst();
            }

            return queue.poll();
        }
    }

}
