package com.aaron.LeetCode;


import java.util.*;

//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。 
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。 
//
// 
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。 
// 
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 
//
// 提示： 
//
// 
// 1 <= numCourses <= 10⁵ 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 1641 👎 0


/**
 * 207, 课程表
 * @author Aaron Zhu
 * @date 2023-7-20
 */
public class CourseSchedule_207{
    
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
    }

    /**
     * 基于入度的拓扑排序
     */
    public static class Solution2 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
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

            // 拓扑排序的结果中包含所有顶点，则说明成功
            if( sortRes.size() == numCourses ) {
                return true;
            }
            // 拓扑排序的结果中未包含所有顶点，则该有向图中存在环，即失败
            return false;
        }

    }

    /**
     * DFS+回溯
     */
    public static class Solution1 {
        private static Set<Integer> emptySet = Collections.emptySet();

        private int total;

        /**
         * key: 课程A, value: 课程A对应的直接前置课程集合
         */
        private Map<Integer, Set<Integer>> preMap;

        /**
         * key: 课程A, value: 课程A对应的所有前置课程集合
         */
        private Map<Integer, Set<Integer>> preAllMap;

        /**
         * 记录遍历时的搜索路径
         */
        private Set<Integer> status;

        private boolean result;

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if( !init(numCourses, prerequisites) ) {
                return false;
            }

            for (int i=0; i<total; i++) {
                dfs(i, i);
                if( !result ) {
                    return false;
                }
            }
            return true;
        }

        private boolean init(int numCourses, int[][] prerequisites) {
            total = numCourses;
            preMap = new HashMap<>();
            for (int[] pre : prerequisites) {
                if( pre[0]==pre[1]  ) {
                    return false;
                }
                preMap.computeIfAbsent(pre[0], HashSet::new )
                    .add(pre[1]);
            }
            preAllMap = new HashMap<>();
            status = new HashSet<>();
            result = true;
            return true;
        }

        private Set<Integer> dfs(Integer start, int curNum) {
            // 剪枝
            if( !result ) {
                return emptySet;
            }

            Set<Integer> nexts = preMap.get(curNum);
            if( nexts==null || nexts.isEmpty() ) {
                return emptySet;
            }

            Set<Integer> curSet = new HashSet<>();

            for (Integer next : nexts) {
                // 剪枝
                if( !result ) {
                    break;
                }

                // 遍历到起始顶点
                if ( start.equals(next) ) {
                    result = false;
                    break;
                }

                if( preAllMap.containsKey(next) ) {
                    Set<Integer> allNode = preAllMap.get(next);
                    // 子树中包含起始顶点
                    if( allNode.contains(start) ) {
                        result = false;
                        break;
                    }
                    // 当前搜索路径 与 子树中某条分支 会成环
                    Set<Integer> smallSet = allNode.size()<=status.size() ? allNode : status;
                    Set<Integer> bigSet = smallSet==allNode ? status : allNode;
                    for (Integer num : smallSet) {
                        if( bigSet.contains(num) ) {
                            result = false;
                            break;
                        }
                    }
                    // 剪枝
                    if( !result ) {
                        break;
                    }

                    curSet.add(next);
                    curSet.addAll( allNode );
                    continue;
                }

                if( status.add(next) ) {    // 成功做出选择
                    Set<Integer> tempSet = dfs(start, next);
                    // 剪枝
                    if( !result ) {
                        break;
                    }
                    status.remove(next);    // 撤销选择

                    curSet.addAll(tempSet);
                    curSet.add( next );
                } else {    // 做出选择失败, 说明单链成环
                    result = false;
                    break;
                }
            }

            preAllMap.put(curNum, curSet);
            return curSet;
        }
    }

}