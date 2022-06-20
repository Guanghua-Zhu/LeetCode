package com.aaron.LeetCode;

import com.sun.tools.classfile.ConstantPool;

import java.util.*;

//请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。 
//
// 实现 LRUCache 类： 
//
// 
// 
// 
// LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 
//key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。 
// 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
// 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 10⁵ 
// 最多调用 2 * 10⁵ 次 get 和 put 
// 
// Related Topics 设计 哈希表 链表 双向链表 👍 2228 👎 0


/**
 * 146, LRU 缓存
 * @author Aaron Zhu
 * @date 2022-6-20
 */
public class LruCache_146{

    public static void main(String[] args) {
    }

    public static class LRUCache {
        private Map<Integer, Node> map;

        private Node head;

        private Node tail;

        private int cap;

        private int size;

        public LRUCache(int capacity) {
            map = new HashMap<>();
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
            cap = capacity;
            size = 0;
        }

        public int get(int key) {
            if( !map.containsKey(key) ) {
                return -1;
            }

            Node node = map.get(key);
            move2Head(node);
            return node.val;
        }

        public void put(int key, int value) {
            if( map.containsKey(key) ) {
                Node node = map.get(key);
                node.val = value;
                move2Head(node);
                return;
            }

            Node node = new Node(key, value);
            map.put(key, node);
            size++;
            add2Head(node);

            if( size>cap ) {
                Node node1 = tail.prev;
                map.remove( node1.key );
                size--;
                removeTail();
            }

        }

        /**
         * 将节点移动到队首
         * @param node
         */
        private void move2Head(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }

        /**
         * 添加元素到队首
         * @param node
         */
        private void add2Head(Node node) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }

        /**
         * 移除队尾元素
         */
        private void removeTail() {
            tail.prev.prev.next = tail;
            tail.prev = tail.prev.prev;
        }

        private static class Node {
            private Node prev;

            private Node next;

            private int key;

            private int val;

            public Node() {
            }

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
    }

    public static class LRUCache1 {

        private Cache cache;

        public LRUCache1(int capacity) {
            this.cache = new Cache(capacity);
        }

        public int get(int key) {
            Integer res = cache.get(key);
            if( res==null ) {
                return -1;
            }
            return res;
        }

        public void put(int key, int value) {
            cache.put(key,value);
        }

        private static class Cache extends LinkedHashMap<Integer, Integer> {
            private int cap;

            public Cache(int cap) {
                super(16,0.75f, true);
                this.cap = cap;
            }

            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return this.size() > cap;
            }
        }
    }

}