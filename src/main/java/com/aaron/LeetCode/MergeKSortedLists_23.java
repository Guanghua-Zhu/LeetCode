package com.aaron.LeetCode;

import java.util.*;

//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 链表 分治 堆（优先队列） 归并排序 
// 👍 1872 👎 0


/**
 * 23, 合并K个升序链表
 * @author Aaron Zhu
 * @date 2022-4-6
 */
public class MergeKSortedLists_23{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode list1 = new ListNode(-2);
        list1.next = new ListNode(-1);
        list1.next.next = new ListNode(-1);
        list1.next.next.next = new ListNode(-1);

        ListNode[] lists = new ListNode[]{list1, null};
        solution.mergeKLists(lists);
        System.out.println("gg");
    }

    /**
     * 堆排序: 每次只对表头元素进行排序
     */
    public static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }

            Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
            for (ListNode listNode : lists) {
                if( listNode!=null ) {
                    queue.add( listNode );
                }
            }

            ListNode head = new ListNode();
            ListNode current = head;
            while (!queue.isEmpty()) {
                ListNode listNode = queue.poll();
                if( listNode.next!=null ) {
                    queue.add( listNode.next );
                }

                current.next = listNode;
                current = current.next;
            }

            return head.next;
        }
    }

    /**
     * 堆排序: 暴力法
     */
    public static class Solution2 {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }

            Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
            for (ListNode listNode : lists) {
                ListNode current = listNode;
                while (current != null) {
                    queue.add(current);

                    // 移除节点的后继指针, 防止成环
                    ListNode temp = current.next;
                    current.next = null;
                    current = temp;
                }
            }

            ListNode head = new ListNode();
            ListNode current = head;
            while (!queue.isEmpty()) {
                ListNode listNode = queue.poll();
                current.next = listNode;
                current = current.next;
            }

            return head.next;
        }
    }

    /**
     * 归并排序
     */
    public static class Solution1 {
        public ListNode mergeKLists(ListNode[] lists) {
            if( lists==null || lists.length==0 ) {
                return null;
            }

            boolean allNull = true;
            for(ListNode listNode : lists) {
                if( listNode!=null ) {
                    allNull = false;
                    break;
                }
            }
            if( allNull ) {
                return null;
            }


            List<ListNode> allList = new ArrayList<>( Arrays.asList(lists) );

            while ( allList.size()>1 ) {
                List<ListNode> tempList = new ArrayList<>();

                int listCount = allList.size();
                for(int j=0; j<listCount; j=j+2) {
                    ListNode resultList = null;
                    if( j+1>=listCount ) {
                        resultList = allList.get(j);
                    } else {
                        resultList = merge( allList.get(j), allList.get(j+1) );
                    }

                    if( resultList!=null ) {
                        tempList.add( resultList );
                    }
                }

                allList = tempList;
            }

            return allList.get(0);
        }

        private ListNode merge(ListNode l1, ListNode l2) {
            if( l1==null && l2==null ) {
                return null;
            } else if ( l1==null ) {
                return l2;
            } else if ( l2==null ) {
                return l1;
            }

            ListNode head = new ListNode();
            ListNode current = head;
            while ( l1!=null || l2!=null ) {
                if( l1==null ) {
                    current.next = l2;
                    l2 = l2.next;
                } else if ( l2==null ) {
                    current.next = l1;
                    l1 = l1.next;
                } else if ( l1.val < l2.val ) {
                    current.next = l1;
                    l1 = l1.next;
                } else if ( l2.val <= l1.val ) {
                    current.next = l2;
                    l2 = l2.next;
                }
                current = current.next;
            }

            return head.next;
        }

    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}