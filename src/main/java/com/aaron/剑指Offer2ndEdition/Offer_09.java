package com.aaron.剑指Offer2ndEdition;

import java.util.LinkedList;

//用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的
//功能。(若队列中没有元素，deleteHead 操作返回 -1 ) 
//
// 
//
// 示例 1： 
//
// 输入：
//["CQueue","appendTail","deleteHead","deleteHead"]
//[[],[3],[],[]]
//输出：[null,null,3,-1]
// 
//
// 示例 2： 
//
// 输入：
//["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
//[[],          [],          [5],          [2],         [],         []]
//输出：[null,    -1,         null,          null,         5,         2]
// 
//
// 提示： 
//
// 
// 1 <= values <= 10000 
// 最多会对 appendTail、deleteHead 进行 10000 次调用 
// 
// Related Topics 栈 设计 队列 
// 👍 393 👎 0

/**
 * 剑指offer·第二版: 09, 用两个栈实现一个队列
 * @author Aaron Zhu
 * @date 2022-1-2
 */
public class Offer_09{
    public static void main(String[] args) {

    }

    /**
     * 借助两个栈实现
     */
    public static class CQueue2 {
        // 用于入栈
        private LinkedList<Integer> stackA;

        // 用于出栈
        private LinkedList<Integer> stackB;

        public CQueue2() {
            stackA = new LinkedList();
            stackB = new LinkedList();
        }

        public void appendTail(int value) {
            // 添加元素直接入栈
            stackA.addLast(value);
        }

        public int deleteHead() {
            // B栈不为空, 直接出栈
            if( !stackB.isEmpty() ) {
                return stackB.removeLast();
            }

            // A、B栈均为空, 说明无元素
            if( stackA.isEmpty() ) {
                return -1;
            }

            while ( !stackA.isEmpty() ) {
                Integer e = stackA.removeLast();
                stackB.addLast( e );
            }
            return stackB.removeLast();
        }
    }

    /**
     * 直接使用List
     */
    public static class CQueue1 {

        private LinkedList<Integer> list;

        public CQueue1() {
            list = new LinkedList();
        }

        public void appendTail(int value) {
            list.offer(value);
        }

        public int deleteHead() {
            Integer result = list.poll();
            if( result==null ) {
                result = -1;
            }
            return result;
        }
    }
}

