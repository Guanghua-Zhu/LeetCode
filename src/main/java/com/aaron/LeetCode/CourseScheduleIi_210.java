package com.aaron.LeetCode;

import java.util.*;

//现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 
//prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。 
//
// 
// 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。 
// 
//
// 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：[0,1]
//解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
// 
//
// 示例 2： 
//
// 
//输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//输出：[0,2,1,3]
//解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
//因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。 
//
// 示例 3： 
//
// 
//输入：numCourses = 1, prerequisites = []
//输出：[0]
// 
//
// 
//提示：
//
// 
// 1 <= numCourses <= 2000 
// 0 <= prerequisites.length <= numCourses * (numCourses - 1) 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// ai != bi 
// 所有[ai, bi] 互不相同 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 796 👎 0


/**
 * 210, 课程表 II
 * @author Aaron Zhu
 * @date 2023-7-20
 */
public class CourseScheduleIi_210{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * 基于Kahn算法的拓扑排序
     */
    public static class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // Key: 有向边的起点；Value: 以Key为起点的所有有向边的终点集合
            Map<Integer, Set<Integer>> edges = new HashMap<>();
            // 各顶点的入度
            int[] indge = new int[numCourses];

            for (int[] edge : prerequisites) {
                int start = edge[1];    // 有向边的起点
                int end = edge[0];      // 有向边的终点
                // 记录有向图的边
                edges.computeIfAbsent(start, HashSet::new).add( end );
                // 统计各顶点的入度
                indge[end]++;
            }

            // 将所有入度为0的顶点 加入队列
            Queue<Integer> queue = new LinkedList<>();
            for (int i=0; i<numCourses; i++) {
                if( indge[i]==0 ) {
                    queue.offer(i);
                }
            }

            List<Integer> sortRes = new ArrayList<>(); // 拓扑排序结果
            while (!queue.isEmpty()) {
                // 入度为0的顶点可安全从有向图中删除, 同时加入拓扑排序的结果
                int node = queue.poll();
                sortRes.add(node);
                // 对于以该顶点为起点的所有边而言，从有向图中删除该顶点后，
                // 相应边同时被删除，故相应边终点的入度也会减少1
                for (Integer end : edges.getOrDefault(node, Collections.emptySet()) ){
                    indge[end]--;
                    // 对于入度减为0的顶点而言，说明其后续可以安全地从图中删除，故加入队列
                    if( indge[end]==0 ) {
                        queue.offer(end);
                    }
                }
            }

            int[] res = new int[0];
            // 拓扑排序的结果中包含所有顶点，则说明成功
            // 反之，拓扑排序的结果中未包含所有顶点，则该有向图中存在环，即失败
            if( sortRes.size() == numCourses ) {
                res = sortRes.stream()
                    .mapToInt(Integer::valueOf)
                    .toArray();
            }
            return res;
        }

    }

}