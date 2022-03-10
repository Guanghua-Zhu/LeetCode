package com.aaron.剑指Offer2ndEdition;

import java.util.*;

//如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数
//值排序之后中间两个数的平均值。 
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例 1： 
//
// 输入：
//["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
//[[],[1],[2],[],[3],[]]
//输出：[null,null,null,1.50000,null,2.00000]
// 
//
// 示例 2： 
//
// 输入：
//["MedianFinder","addNum","findMedian","addNum","findMedian"]
//[[],[2],[],[3],[]]
//输出：[null,null,2.00000,null,2.50000] 
//
// 
//
// 限制： 
//
// 
// 最多会对 addNum、findMedian 进行 50000 次调用。 
// 
//
// 注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-strea
//m/ 
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 
// 👍 268 👎 0


/**
 * 剑指 Offer 41, 数据流中的中位数
 * @author Aaron Zhu
 * @date 2022-3-10
 */
public class Offer_41 {
    public static void main(String[] args) {
    }

    /**
     * 最大堆、最小堆
     */
    public static class MedianFinder {
        /**
         * A堆: 最小堆, 存储较大的元素
         */
        private PriorityQueue<Integer> aHeap;

        /**
         * A堆: 最大堆, 存储较小的元素
         */
        private PriorityQueue<Integer> bHeap;

        /** initialize your data structure here. */
        public MedianFinder() {
            aHeap = new PriorityQueue<>();
            bHeap = new PriorityQueue<>( Comparator.reverseOrder() );
        }

        public void addNum(int num) {
            if(aHeap.size() == bHeap.size()) {
                bHeap.offer( num );
                aHeap.offer( bHeap.poll() );
            } else {
                aHeap.offer( num );
                bHeap.offer( aHeap.poll() );
            }
        }

        public double findMedian() {
            if( aHeap.size() == bHeap.size() ) {
                return (aHeap.peek()+bHeap.peek()) / 2.0;
            } else {
                return aHeap.peek();
            }

        }
    }

    /**
     * 插入排序法
     */
    public static class MedianFinder1 {
        private List<Integer> list;

        /** initialize your data structure here. */
        public MedianFinder1() {
            this.list = new ArrayList<>();
        }

        public void addNum(int num) {
            list.add(num);
            if( list.size()==1 ) {
                return;
            }

            int i;
            for (i=list.size()-2; i>=0; i--) {
                if( list.get(i)>num ) {
                    list.set(i+1, list.get(i));
                } else {
                    break;
                }
            }
            list.set(i+1, num);
        }

        public double findMedian() {
            int index = list.size()/2;
            double res;
            if( list.size()%2==0 ) {    // 偶数
                res = ( list.get(index-1)+list.get(index) ) / 2.0;
            } else {    // 奇数
                res = list.get(index);
            }
            return res;
        }
    }
}
