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
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 1320 👎 0


/**
 * 207, 课程表
 * @author Aaron Zhu
 * @date 2022-6-29
 */
public class CourseSchedule_207{
    
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    /**
     * BFS
     */
    public static class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if(numCourses<=1 || prerequisites.length<=1) {
                return true;
            }

            // index 指向边列表
            Set[] edges = new Set[numCourses];
            // 每个顶点的入度
            int[] indeg = new int[numCourses];
            for(int[] edge : prerequisites) {
                int startPoint = edge[1];
                int endPoint = edge[0];
                if( edges[startPoint]==null ) {
                    edges[startPoint] = new HashSet();
                }
                // 记录有向边的关系
                edges[startPoint].add( endPoint );
                // 记录度数
                indeg[endPoint]++;
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int i=0; i<numCourses; i++) {
                if( indeg[i]==0 ) {
                    queue.offer(i);
                }
            }

            int complete = 0;
            while ( !queue.isEmpty() ) {
                complete++;
                int startPoint = queue.poll();
                if( edges[startPoint]!=null ) {
                    for (Object endPointObj : edges[startPoint]) {
                        Integer endPoint = (Integer) endPointObj;
                        indeg[endPoint]--;
                        if( indeg[endPoint]==0 ) {
                            queue.offer( endPoint );
                        }
                    }
                }
            }

            boolean res =  complete == numCourses;
            return res;
        }
    }

    /**
     * 暴力
     */
    public static class Solution1 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if(numCourses<=1 || prerequisites.length<=1) {
                return true;
            }

            Set[] sets = new HashSet[numCourses];
            for (int[] pre : prerequisites) {
                int a = pre[0];
                int b = pre[1];
                if( sets[a]==null ) {
                    sets[a] = new HashSet<>();
                }
                sets[a].add( b );

                if( sets[b]==null ) {
                    continue;
                }

                if( sets[b].contains(a) ) {
                    return false;
                }

                Set<Integer> tempSet = sets[b];
                while ( !tempSet.isEmpty() ) {
                    Set<Integer> tempSet2 = new HashSet<>();
                    for (int temp : tempSet) {
                        if(sets[temp]!=null) {
                            if( sets[temp].contains(a) ) {
                                return false;
                            }
                            tempSet2.addAll( sets[temp] );
                        }
                    }
                    tempSet = tempSet2;
                }
            }

            return true;
        }
    }

}
