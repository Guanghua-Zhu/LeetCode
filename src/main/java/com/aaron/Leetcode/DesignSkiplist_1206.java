package com.aaron.Leetcode;

import java.util.*;

//不使用任何库函数，设计一个 跳表 。 
//
// 跳表 是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思
//想与链表相似。 
//
// 例如，一个跳表包含 [30, 40, 50, 60, 70, 90] ，然后增加 80、45 到跳表中，以下图的方式操作： 
//
// 
//Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons 
//
// 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(
//n))，空间复杂度是 O(n)。 
//
// 了解更多 : https://en.wikipedia.org/wiki/Skip_list 
//
// 在本题中，你的设计应该要包含这些函数： 
//
// 
// bool search(int target) : 返回target是否存在于跳表中。 
// void add(int num): 插入一个元素到跳表。 
// bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。 
//
// 
//
// 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。 
//
// 
//
// 示例 1: 
//
// 
//输入
//["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase",
// "search"]
//[[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
//输出
//[null, null, null, null, false, null, true, false, true, false]
//
//解释
//Skiplist skiplist = new Skiplist();
//skiplist.add(1);
//skiplist.add(2);
//skiplist.add(3);
//skiplist.search(0);   // 返回 false
//skiplist.add(4);
//skiplist.search(1);   // 返回 true
//skiplist.erase(0);    // 返回 false，0 不在跳表中
//skiplist.erase(1);    // 返回 true
//skiplist.search(1);   // 返回 false，1 已被擦除
// 
//
// 
//
// 提示: 
//
// 
// 0 <= num, target <= 2 * 104 
// 调用search, add, erase操作次数不大于 5 * 104 
// 
// Related Topics 设计 链表 
// 👍 106 👎 0


/**
 * 1206, 设计跳表
 * @author Aaron Zhu
 * @date 2022-3-12
 */
public class DesignSkiplist_1206{
    public static void main(String[] args) {
    }

    /**
     * 跳表
     */
    public static class Skiplist {
        /**
         * 跳表的最大层数
         */
        private static final int MAX_LEVEL = 15;

        /**
         * 跳表第i层的元素出现在第i+1层的概率
         */
        private static final double p = 0.5;

        /**
         * 表头
         */
        private Node head;

        /**
         * 跳表层数, 从0开始计数
         */
        private int currentLevel;

        public Skiplist() {
            this.head = new Node(null, MAX_LEVEL);
            this.currentLevel = 0;
        }

        /**
         * 添加一个元素到跳表中
         * @param num
         */
        public void add(int num) {
            // 随机生成层数
            int newLevel = getRandomLevel();
            // 创建节点实例
            Node newNode = new Node(num, newLevel);
            Node node = head;
            for (int level = currentLevel; level>=0; level--) {
                if( level > newLevel ) {
                    continue;
                }
                // 找到欲插入num的前一个节点
                node = getLastLessThanNum(node, num, level);
                // 将节点实例插入到当前层的链表中
                Node temp = node.next[level];
                node.next[level] = newNode;
                newNode.next[level] = temp;
            }

            // 新节点层数 超过 跳表原层数
            if( newLevel>currentLevel ) {
                // 新增层数链表的表头直接 指向 新节点 即可
                for(int level = newLevel; level>currentLevel; level--) {
                    head.next[level] = newNode;
                }
                // 更新跳表层数
                currentLevel = newLevel;
            }
        }

        /**
         * 判断当前元素是否存在于跳表中
         * @param num
         * @return
         */
        public boolean search(int num) {
            Node node = head;
            for(int level=currentLevel; level>=0; level--) {
                node = getLastLessThanNum(node, num, level);
                // 在跳表当前层找到指定元素
                if( node.next[level]!=null && node.next[level].val.equals(num) ) {
                    return true;
                }
            }

            return false;
        }

        /**
         * 从跳表中删除一个元素
         * @param num
         * @return 如果num不存在, 直接返回false; 如果存在多个num 删除其中任意一个即可
         */
        public boolean erase(int num) {
            // 删除成功标记
            boolean flag = false;
            Node node = head;
            for (int level=currentLevel; level>=0; level--) {
                node = getLastLessThanNum(node, num, level);
                // 在跳表当前层找到指定元素
                if( node.next[level]!=null && node.next[level].val.equals(num) ) {
                    flag = true;
                    // 链表的节点删除: 将node 指向 指定元素的后继节点
                    node.next[level] = node.next[level].next[level];
                }
            }

            // 重新计算跳表的层数
            for(int level=0; level<=MAX_LEVEL; level++) {
                if(head.next[level] == null) {
                    currentLevel = level-1;
                    break;
                }
            }

            return flag;
        }

        /**
         * 获取一个随机层数, 范围: [0, 15]
         * @return
         */
        private int getRandomLevel() {
            int level = 0;
            while (Math.random()<p && level<MAX_LEVEL) {
                level++;
            }
            return level;
        }

        /**
         * 在跳表指定层的链表中寻找最后一个小于num的节点
         * @param node 链表
         * @param num
         * @param level 跳表层数
         * @return
         */
        private Node getLastLessThanNum(Node node, Integer num, int level) {
            Node current = node;
            while ( current.next[level]!=null && current.next[level].val<num ) {
                current = current.next[level];
            }
            return current;
        }

        /**
         * 跳表节点
         */
        private static class Node {
            private Integer val;

            private Node[] next;

            public Node(Integer val, int size) {
                this.val = val;
                this.next = new Node[size+1];
            }
        }
    }

}

